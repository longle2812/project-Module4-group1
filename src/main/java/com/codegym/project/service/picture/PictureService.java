package com.codegym.project.service.picture;

import com.codegym.project.model.Picture;
import com.codegym.project.repository.IPictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PictureService implements IPictureService{
    @Autowired
    private IPictureRepository pictureRepository;

    @Override
    public Iterable<Picture> findAll() {
        return pictureRepository.findAll();
    }

    @Override
    public Optional<Picture> findById(Long id) {
        return pictureRepository.findById(id);
    }

    @Override
    public Picture save(Picture picture) {
        return pictureRepository.save(picture);
    }

    @Override
    public Iterable<Picture> findPictureByProductId(Long id) {
        return pictureRepository.findPictureByProductId(id);
    }

    @Override
    public void remove(Long id) {
        pictureRepository.deleteById(id);
    }
}
