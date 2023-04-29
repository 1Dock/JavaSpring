package kz.bitlab.Lesson4.repositories;

import kz.bitlab.Lesson4.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findAllByHandled(boolean handled);
}
