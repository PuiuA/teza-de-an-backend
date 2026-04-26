package com.proiect.repository;

import com.proiect.model.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AthleteRepository extends JpaRepository<Athlete, Long> {
    List<Athlete> findByNameContainingIgnoreCase(String name);
    List<Athlete> findByClubContainingIgnoreCase(String club);
}
