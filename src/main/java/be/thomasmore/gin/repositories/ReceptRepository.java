package be.thomasmore.gin.repositories;

import be.thomasmore.gin.model.Recept;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ReceptRepository extends CrudRepository<Recept,Integer> {
    Optional<Recept> findFirstByIdLessThanOrderByIdDesc(int id);

    Optional<Recept> findFirstByOrderByIdDesc();

    Optional<Recept> findFirstByIdGreaterThanOrderByIdAsc(int id);

    Optional<Recept> findFirstByOrderByIdAsc();

    Iterable<Recept> findByWater(boolean yes);
}
