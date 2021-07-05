package com.codegym.project.service.collection;

import com.codegym.project.model.Collection;
import com.codegym.project.repository.ICollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CollectionService implements ICollectionService{
    @Autowired
    private ICollectionRepository collectionRepository;

    @Override
    public Iterable<Collection> findAll() {
        return collectionRepository.findAll();
    }

    @Override
    public Optional<Collection> findById(Long id) {
        return collectionRepository.findById(id);
    }

    @Override
    public Collection save(Collection collection) {
        return collectionRepository.save(collection);
    }

    @Override
    public void remove(Long id) {
        collectionRepository.deleteById(id);
    }
}
