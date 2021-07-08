package com.codegym.project.service.product;

import com.codegym.project.model.Collection;
import com.codegym.project.model.Product;
import com.codegym.project.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface IProductService extends IGeneralService<Product> {
    Iterable<Product> findProductByCollectionIds(Long id);
    Page<Product> findProductByBrandIdsAndPrice(Set<Long> ids, double min, double max, Pageable pageable);
    Page<Product> findAllPage(Pageable pageable);
    Iterable<Product> findProductByNameContaining(String keyword);
}
