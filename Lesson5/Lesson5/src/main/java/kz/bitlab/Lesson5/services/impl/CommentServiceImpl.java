package kz.bitlab.Lesson5.services.impl;

import kz.bitlab.Lesson5.repositories.CommentRepository;
import kz.bitlab.Lesson5.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
}
