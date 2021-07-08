package com.codegym.project.service.comment;

import com.codegym.project.model.Blog;
import com.codegym.project.model.Comment;
import com.codegym.project.repository.ICommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService implements ICommentService{
    @Autowired
    private ICommentRepository commentRepository;

    @Override
    public Iterable<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void remove(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public Iterable<Comment> findByBlog(Blog blog) {
        return commentRepository.findByBlog(blog);
    }
}
