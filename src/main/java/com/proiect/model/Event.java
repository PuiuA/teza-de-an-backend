package com.proiect.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "event", schema="judo_schema")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long id;

    @Column(name = "title", nullable=false)
    private String title;

    @Column(name = "description", nullable=false)
    private String description;

    @Column(name = "short_description", nullable=false)
    private String shortDescription;

    @Column(name = "published", nullable=false)
    private LocalDateTime published;

    @JoinColumn(name = "event_type_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private EventType eventType;

    @Column(name = "image")
    private byte[] image;

    @Column(name = "links")
    private List<String> links;

    @Column(name = "author")
    private String author;

    @JoinColumn(name = "categories")
    @OneToMany(fetch = FetchType.LAZY)
    private List<Category> categories;

    @Column(name = "information")
    private String information;

}
