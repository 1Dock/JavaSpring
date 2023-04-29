package kz.bitlab.Lesson4.services;

import kz.bitlab.Lesson4.entity.Course;

import java.util.List;

public interface CourseService {
    List<Course> findAll();

    Course findById(Long id);
}
