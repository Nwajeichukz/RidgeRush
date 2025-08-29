package com.RidgeRush;

import com.RidgeRush.entity.Race;
import com.RidgeRush.entity.RaceResult;
import com.RidgeRush.entity.Rider;
import com.RidgeRush.repository.RaceResultRepository;
import com.RidgeRush.repository.RidersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.PageRequest;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class RaceRepositoriesTest {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private RaceResultRepository raceResultRepository;

    @Autowired
    private RidersRepository ridersRepository;

    private Race race;

    @BeforeEach
    void setup() {
        // Clear persistence context to ensure clean test state
        entityManager.clear();

        race = Race.builder()
                .name("Annual Mountain Challenge")
                .location("Alps")
                .build();
        entityManager.persist(race);

        Rider rider1 = Rider.builder()
                .firstName("habib")
                .lastName("goke")
                .email("habib@example.com")
                .build();
        entityManager.persist(rider1);

        Rider rider2 = Rider.builder()
                .firstName("Jane")
                .lastName("Smith")
                .email("jane@example.com")
                .build();
        entityManager.persist(rider2);

        Rider rider3 = Rider.builder()
                .firstName("Tom")
                .lastName("Brown")
                .email("tom@example.com")
                .build();
        entityManager.persist(rider3);

        Rider rider4 = Rider.builder()
                .firstName("Alice")
                .lastName("White")
                .email("alice@example.com")
                .build();
        entityManager.persist(rider4);

        // Race results
        RaceResult result1 = RaceResult.builder()
                .race(race)
                .rider(rider1)
                .totalTimeSeconds(300)
                .didNotFinish(false)
                .build();
        entityManager.persist(result1);

        RaceResult result2 = RaceResult.builder()
                .race(race)
                .rider(rider2)
                .totalTimeSeconds(250)
                .didNotFinish(false)
                .build();
        entityManager.persist(result2);

        RaceResult result3 = RaceResult.builder()
                .race(race)
                .rider(rider3)
                .didNotFinish(true)
                .totalTimeSeconds(0)
                .build();
        entityManager.persist(result3);

        // Rider4 did NOT participate
        entityManager.flush();
    }

    @Test
    void testFindTop3FastestRiders() {
        List<String> fastestRiders = raceResultRepository.findTop3FastestRiders(
                race.getId(), PageRequest.of(0, 3)
        );

        assertThat(fastestRiders)
                .containsExactly("Jane Smith", "habib goke");
    }

    @Test
    void testFindRiderNamesWhoDidNotFinish() {
        List<String> didNotFinish = raceResultRepository.findRiderNamesWhoDidNotFinish(race.getId());

        assertThat(didNotFinish)
                .containsExactly("Tom Brown");
    }

    @Test
    void testFindRiderNamesWhoDidNotParticipate() {
        List<String> nonParticipants = ridersRepository.findRiderNamesWhoDidNotParticipate(race.getId());

        assertThat(nonParticipants)
                .containsExactly("Alice White");
    }

    @Test
    void testExistsByEmail() {
        boolean exists = ridersRepository.existsByEmail("habib@example.com");
        boolean notExists = ridersRepository.existsByEmail("nonexistent@example.com");

        assertThat(exists).isTrue();
        assertThat(notExists).isFalse();
    }

}
