package kz.bitlab.Lesson4.services;

import kz.bitlab.Lesson4.entity.Request;

import java.util.List;

public interface RequestService {
    List<Request> findAll();

    Request findById(Long id);

    void deleteById(Long id);

    List<Request> findAllByHandled(boolean Handle);

    Request process(Request request, List<String> operatorIds);

    Request update(Request request);

    Request add(Request request);
}
