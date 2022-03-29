package be.thomasmore.gin.repositories;

import be.thomasmore.gin.model.Brand;
import be.thomasmore.gin.model.Recept;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BrandRepository extends CrudRepository<Brand,Integer> {
    Optional<Brand> findFirstByIdLessThanOrderByIdDesc(int id);

    Optional<Brand> findFirstByOrderByIdDesc();

    Optional<Brand> findFirstByIdGreaterThanOrderByIdAsc(int id);

    Optional<Brand> findFirstByOrderByIdAsc();

    Iterable<Brand> findBylondondry(boolean yes);

    Iterable<Brand> findByspiced(boolean yes);
}
