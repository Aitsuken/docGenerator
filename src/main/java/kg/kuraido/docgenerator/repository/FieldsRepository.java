package kg.kuraido.docgenerator.repository;

import kg.kuraido.docgenerator.model.Fields;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FieldsRepository extends JpaRepository<Fields, Long> {
    Fields findById(long id);
    List<Fields> findAll();
}
