package kz.bitlab.Lesson4.services.impl;

import kz.bitlab.Lesson4.entity.Operator;
import kz.bitlab.Lesson4.entity.Request;
import kz.bitlab.Lesson4.repositories.OperatorRepository;
import kz.bitlab.Lesson4.repositories.RequestRepository;
import kz.bitlab.Lesson4.services.OperatorService;
import kz.bitlab.Lesson4.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {
    @Autowired
    RequestRepository requestRepository;
    @Autowired
    OperatorService operatorService;

    @Override
    public List<Request> findAll() {
        return requestRepository.findAll();
    }

    @Override
    public Request findById(Long id) {
        return requestRepository.findById(id).get();
    }

    @Override
    public void deleteById(Long id) {
        requestRepository.deleteById(id);
    }

    @Override
    public List<Request> findAllByHandled(boolean handled) {
        return requestRepository.findAllByHandled(handled);
    }

    @Override
    public Request update(Request request) {
        return requestRepository.save(request);
    }

    @Override
    public Request process(Request request, List<String> operatorIds) {
        request = requestRepository.save(request);

        if (operatorIds == null) return request;

        ArrayList<Operator> operators = new ArrayList<>();

        for (String id : operatorIds) {
            Operator operator = operatorService.findById(Long.parseLong(id));
            operators.add(operator);
        }

        request.setOperators(operators);

        return requestRepository.save(request);
    }

    @Override
    public Request add(Request request) {
        return requestRepository.save(request);
    }
}
