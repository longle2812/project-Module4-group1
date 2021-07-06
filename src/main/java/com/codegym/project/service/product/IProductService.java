package com.codegym.project.service.product;

import com.codegym.project.model.Collection;
import com.codegym.project.model.Product;
import com.codegym.project.service.IGeneralService;

import java.awt.print.Pageable;

public interface IProductService extends IGeneralService<Product> {
    Iterable<Product> findProductByCollection(Collection collection);
}
