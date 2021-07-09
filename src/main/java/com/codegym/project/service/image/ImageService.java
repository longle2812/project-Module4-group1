package com.codegym.project.service.image;

import com.codegym.project.model.Image;
import com.codegym.project.repository.IImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageService implements IImageService{
    @Autowired
    private IImageRepository iImageRepository;

    @Override
    public Iterable<Image> findAll() {
        return iImageRepository.findAll();
    }

    @Override
    public Optional<Image> findById(Long id) {
        return iImageRepository.findById(id);
    }

    @Override
    public Image save(Image image) {
        return iImageRepository.save(image);
    }

    @Override
    public Image findImageByName(String name) {
        return iImageRepository.findImageByName(name);
    }

    @Override
    public void remove(Long id) {
        iImageRepository.deleteById(id);
    }

    @Override
    public Image findImageByName(String name) {
        return iImageRepository.findImageByName(name);
    }
}
