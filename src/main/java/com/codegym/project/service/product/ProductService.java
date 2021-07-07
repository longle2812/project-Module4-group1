package com.codegym.project.service.product;

import com.codegym.project.model.Collection;
import com.codegym.project.model.Product;
import com.codegym.project.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ProductService implements IProductService{
    @Autowired
    private IProductRepository productRepository;

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Iterable<Product> findProductByCollection(Collection collection) {
        return productRepository.findProductByCollection(collection);
    }

    @Override
    public Iterable<Product> findProductByBrandIdsAndPrice(Set<Long> ids, double min, double max) {
        return productRepository.findProductByBrandIdsAndPrice(ids, min, max);
    }

    @Override
    public Page<Product> findAllPage(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public void remove(Long id) {
        productRepository.deleteById(id);
    }
}
