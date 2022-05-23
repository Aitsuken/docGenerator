package kg.kuraido.docgenerator.repository;

import kg.kuraido.docgenerator.model.Fields;
import org.aspectj.apache.bcel.util.Repository;
import org.springframework.data.repository.CrudRepository;

public interface FieldsCrud extends CrudRepository<Fields, Long> {
    long count();
}
