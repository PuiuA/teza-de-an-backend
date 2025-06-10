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
@Table(name = "news", schema="judo_moldova")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "news_id")
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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "news_link", schema = "judo_moldova",
            joinColumns = @JoinColumn(name = "news_id"),
            inverseJoinColumns = @JoinColumn(name = "link_id")
    )
    private List<Link> links;

    @Column(name = "author")
    private String author;

    @Column(name = "information")
    private String information;

}
