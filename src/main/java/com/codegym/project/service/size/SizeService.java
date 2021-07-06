package com.codegym.project.service.size;

import com.codegym.project.model.Size;
import com.codegym.project.repository.ISizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class SizeService implements ISizeService{
    @Autowired
    private ISizeRepository sizeRepository;

    @Override
    public Iterable<Size> findAll() {
        return sizeRepository.findAll();
    }

    @Override
    public Set<Long> getAllIds() {
        return sizeRepository.getAllIds();
    }

    @Override
    public Optional<Size> findById(Long id) {
        return sizeRepository.findById(id);
    }

    @Override
    public Size save(Size size) {
        return sizeRepository.save(size);
    }

    @Override
    public void remove(Long id) {
        sizeRepository.deleteById(id);
    }
}
