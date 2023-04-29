package kz.bitlab.Lesson4.services.impl;

import kz.bitlab.Lesson4.entity.Course;
import kz.bitlab.Lesson4.repositories.CourseRepository;
import kz.bitlab.Lesson4.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseRepository courseRepository;

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course findById(Long id) {
        return courseRepository.findById(id).get();
    }
}
