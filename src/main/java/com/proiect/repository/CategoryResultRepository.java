package com.proiect.repository;

import com.proiect.model.CategoryResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryResultRepository extends JpaRepository<CategoryResult, Long> {
}
