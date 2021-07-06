package com.codegym.project.service.brand;

import com.codegym.project.model.Brand;
import com.codegym.project.service.IGeneralService;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface IBrandService extends IGeneralService<Brand> {
    @Query("select b.id from Brand b")
    Set<Long> getAllIds();
}
