package kz.bitlab.Lesson4.services.impl;

import kz.bitlab.Lesson4.entity.Operator;
import kz.bitlab.Lesson4.repositories.OperatorRepository;
import kz.bitlab.Lesson4.services.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperatorServiceImpl implements OperatorService {
    @Autowired
    OperatorRepository operatorRepository;

    @Override
    public List<Operator> findAll() {
        return operatorRepository.findAll();
    }

    @Override
    public Operator findById(Long id) {
        return operatorRepository.findById(id).get();
    }

    @Override
    public void deleteById(Long id) {
        operatorRepository.deleteById(id);
    }
}
