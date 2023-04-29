package kz.bitlab.Lesson4.services;

import kz.bitlab.Lesson4.entity.Operator;

import java.util.List;

public interface OperatorService {
    List<Operator> findAll();

    Operator findById(Long id);

    void deleteById(Long id);
}
