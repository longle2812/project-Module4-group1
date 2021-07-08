package com.codegym.project.service.color;

import com.codegym.project.model.Color;
import com.codegym.project.repository.IColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ColorService implements IColorService{
    @Autowired
    private IColorRepository colorRepository;

    @Override
    public Iterable findAll() {
        return colorRepository.findAll();
    }

    @Override
    public Optional findById(Long id) {
        return colorRepository.findById(id);
    }

    @Override
    public Color save(Color color) {
        return colorRepository.save(color);
    }

    @Override
    public void remove(Long id) {
        colorRepository.deleteById(id);
    }
}
