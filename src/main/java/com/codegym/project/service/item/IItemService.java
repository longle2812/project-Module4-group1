package com.codegym.project.service.item;

import com.codegym.project.model.Item;
import com.codegym.project.service.IGeneralService;

import java.util.Optional;

public interface IItemService extends IGeneralService<Item> {
    Integer countAllByCartId(Long id);
    Iterable<Item> findAllByCartId(Long id);
    Optional<Item> findItemByProductIdAndCartId(Long productId, Long cartId);
    void deleteItemByProductIdAndCartId (Long productId, Long cartId);
    void deleteItemByCartId(Long cartId);
}
