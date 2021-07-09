package com.codegym.project.repository;

import com.codegym.project.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IItemRepository extends JpaRepository<Item, Long> {
    @Query("select count(i) from Item i where i.cart.id = ?1")
    Integer countAllByCartId(Long id);

    @Query("select i from Item i where i.cart.id = ?1")
    Iterable<Item> findAllByCartId(Long id);

    @Query("select i from Item i where i.product.id = ?1 and i.cart.id = ?2")
    Optional<Item> findItemByProductIdAndCartId(Long productId, Long cartId);
}
