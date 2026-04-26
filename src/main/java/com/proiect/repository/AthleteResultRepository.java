package com.proiect.repository;

import com.proiect.model.AthleteResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AthleteResultRepository extends JpaRepository<AthleteResult, Long> {
    @Modifying
    @Query("DELETE FROM AthleteResult ar WHERE ar.athlete.id = :athleteId")
    void deleteByAthleteId(@Param("athleteId") Long athleteId);
}