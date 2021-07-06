package com.codegym.project.repository;

import com.codegym.project.model.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICollectionRepository extends JpaRepository<Collection, Long> {
    Collection findCollectionById(Long id);
}
