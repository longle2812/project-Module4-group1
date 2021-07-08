package com.codegym.project.service.size;


import com.codegym.project.model.Size;
import com.codegym.project.service.IGeneralService;

import java.util.Set;

public interface ISizeService extends IGeneralService<Size> {
    Set<Long> getAllIds();
}
