package com.codegym.project.service.comment;

import com.codegym.project.model.Blog;
import com.codegym.project.model.Comment;
import com.codegym.project.service.IGeneralService;

public interface ICommentService extends IGeneralService<Comment> {
    Iterable<Comment> findByBlog(Blog blog);
    public boolean containBadWord(Comment comment);
}
