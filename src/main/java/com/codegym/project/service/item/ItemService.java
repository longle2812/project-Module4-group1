package com.codegym.project.service.item;

import com.codegym.project.model.Item;
import com.codegym.project.repository.IItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService implements IItemService{
    @Autowired
    private IItemRepository iItemRepository;

    @Override
    public Iterable<Item> findAll() {
        return iItemRepository.findAll();
    }

    @Override
    public Optional<Item> findById(Long id) {
        return iItemRepository.findById(id);
    }

    @Override
    public Item save(Item item) {
        return iItemRepository.save(item);
    }

    @Override
    public Integer countAllByCartId(Long id) {
        return iItemRepository.countAllByCartId(id);
    }

    @Override
    public Iterable<Item> findAllByCartId(Long id) {
        return iItemRepository.findAllByCartId(id);
    }

    @Override
    public void deleteItemByProductIdAndCartId(Long productId, Long cartId) {
            iItemRepository.deleteItemByProductIdAndCartId(productId, cartId);
    }

    @Override
    public Optional<Item> findItemByProductIdAndCartId(Long productId, Long cartId) {
        return iItemRepository.findItemByProductIdAndCartId(productId, cartId);
    }

    @Override
    public void remove(Long id) {
        iItemRepository.deleteById(id);
    }
}
