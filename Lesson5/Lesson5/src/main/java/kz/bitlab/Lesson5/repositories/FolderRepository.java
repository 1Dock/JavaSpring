package kz.bitlab.Lesson5.repositories;

import kz.bitlab.Lesson5.entity.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface FolderRepository extends JpaRepository<Folder, Long> {
}
