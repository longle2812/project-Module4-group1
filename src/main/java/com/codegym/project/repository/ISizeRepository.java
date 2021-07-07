package com.codegym.project.repository;

import com.codegym.project.model.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ISizeRepository extends JpaRepository<Size, Long> {
    @Query("select b.id from Size b")
    Set<Long> getAllIds();
}
