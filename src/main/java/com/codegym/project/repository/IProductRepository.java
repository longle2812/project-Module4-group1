package com.codegym.project.repository;

import com.codegym.project.model.Collection;
import com.codegym.project.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p from Product p where p.brand.id in ?1 and p.price >= ?2 and p.price <= ?3")
    Page<Product> findProductByBrandIdsAndPrice(Set<Long> ids, double min, double max, Pageable pageable);

    @Query("select p from Product p where p.collection.id = ?1")
    Iterable<Product> findProductByCollectionIds(Long id);

}
