package com.RidgeRush.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
@Builder
@Table(name = "riders")
public class Rider extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(length = 36, nullable = false, updatable = false)
    private String id;

    private String lastName;

    private String firstName;

    private String email;

    @OneToMany(mappedBy = "rider", cascade = CascadeType.ALL)
    private List<RaceResult> raceResults = new ArrayList<>();
}
