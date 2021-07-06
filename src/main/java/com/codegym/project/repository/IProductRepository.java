package com.codegym.project.repository;

import com.codegym.project.model.Collection;
import com.codegym.project.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    Iterable<Product> findProductByCollection(Collection collection);
}
