package com.codegym.project.repository;

import com.codegym.project.model.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IPictureRepository extends JpaRepository<Picture, Long> {
    @Query("select p from Picture p where p.product.id = ?1")
    Iterable<Picture> findPictureByProductId(Long id);
}
