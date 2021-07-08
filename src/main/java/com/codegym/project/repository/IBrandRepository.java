package com.codegym.project.repository;

import com.codegym.project.model.Brand;
import com.codegym.project.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface IBrandRepository extends JpaRepository<Brand, Long> {
    @Query("select b.id from Brand b")
    Set<Long> getAllIds();
}
