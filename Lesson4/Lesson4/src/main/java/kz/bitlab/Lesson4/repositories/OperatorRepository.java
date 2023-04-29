package kz.bitlab.Lesson4.repositories;

import kz.bitlab.Lesson4.entity.Operator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface OperatorRepository extends JpaRepository<Operator, Long> {
}
