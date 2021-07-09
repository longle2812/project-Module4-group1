package com.codegym.project.repository;

import com.codegym.project.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ICartRepository extends JpaRepository<Cart, Long> {
    @Query("select c from Cart c where c.user.id = ?1")
    Cart findCartByUserId(Long id);
}
