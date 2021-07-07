package com.codegym.project.service.picture;

import com.codegym.project.model.Picture;
import com.codegym.project.service.IGeneralService;

public interface IPictureService extends IGeneralService<Picture> {
    Iterable<Picture> findPictureByProductId(Long id);
    Picture findPictureByName(String name);
}
