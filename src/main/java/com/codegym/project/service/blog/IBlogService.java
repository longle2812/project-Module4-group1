package com.codegym.project.service.blog;

import com.codegym.project.model.Blog;
import com.codegym.project.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface IBlogService extends IGeneralService<Blog> {
    Page<Blog>  findAll(Pageable pageable);
    Page<Blog> findByTitleContains(String keyword, Pageable pageable);
}
