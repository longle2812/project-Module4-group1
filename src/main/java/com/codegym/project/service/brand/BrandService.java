package com.codegym.project.service.brand;

import com.codegym.project.model.Brand;
import com.codegym.project.repository.IBrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class BrandService implements IBrandService{
    @Autowired
    private IBrandRepository brandRepository;

    @Override
    public Set<Long> getAllIds() {
        return brandRepository.getAllIds();
    }

    @Override
    public Iterable<Brand> findAll() {
        return brandRepository.findAll();
    }

    @Override
    public Optional<Brand> findById(Long id) {
        return brandRepository.findById(id);
    }

    @Override
    public Brand save(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public void remove(Long id) {
        brandRepository.deleteById(id);
    }
}
