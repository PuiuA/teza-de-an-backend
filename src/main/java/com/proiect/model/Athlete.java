package com.proiect.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "athlete", schema = "judo_moldova")
public class Athlete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "athlete_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "club")
    private String club;

    @Column(name = "birth_year")
    private Integer birthYear;

    @Column(name = "weight_kg")
    private Double weightKg;

    @Column(name = "belt")
    private String belt;

    @Column(name = "photo")
    private byte[] photo;

    @OneToMany(mappedBy = "athlete", fetch = FetchType.LAZY)
    private List<AthleteResult> results;
}