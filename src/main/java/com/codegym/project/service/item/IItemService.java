package com.codegym.project.service.item;

import com.codegym.project.model.Item;
import com.codegym.project.service.IGeneralService;

public interface IItemService extends IGeneralService<Item> {
    Integer countAllByCartId(Long id);
}
