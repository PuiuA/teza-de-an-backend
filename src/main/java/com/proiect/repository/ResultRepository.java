package com.proiect.repository;

import com.proiect.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {

    List<Result> findByYear(String year);

    @Query("SELECT DISTINCT r.year FROM Result r WHERE r.year IS NOT NULL ORDER BY r.year DESC")
    List<String> findDistinctYears();
}