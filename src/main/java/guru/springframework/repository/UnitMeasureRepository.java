package guru.springframework.repository;

import guru.springframework.domain.UnitMeasure;
import org.springframework.data.repository.CrudRepository;

public interface UnitMeasureRepository extends CrudRepository<UnitMeasure, Long> {
}
