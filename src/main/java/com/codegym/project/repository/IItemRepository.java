package com.codegym.project.repository;

import com.codegym.project.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IItemRepository extends JpaRepository<Item, Long> {
    @Query("select count(i) from Item i where i.cart.id = ?1")
    Integer countAllByCartId(Long id);
}
