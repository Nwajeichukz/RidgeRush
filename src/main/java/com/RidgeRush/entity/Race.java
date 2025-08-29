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
@Table(name = "races")
public class Race extends AbstractAuditingEntity{
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(length = 36, nullable = false, updatable = false)
    private String id;

    private String name;

    private String location;

    private String startTime;

    @OneToMany(mappedBy = "race", cascade = CascadeType.ALL)
    private List<RaceResult> raceResults = new ArrayList<>();
}