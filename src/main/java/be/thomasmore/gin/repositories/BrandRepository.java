package be.thomasmore.gin.repositories;

import be.thomasmore.gin.model.Brand;
import be.thomasmore.gin.model.Recept;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BrandRepository extends CrudRepository<Brand,Integer> {
    @Query("SELECT b FROM Brand b WHERE " +
            "(:minIntroduced IS NULL OR :minIntroduced <= b.introduced) AND " +
            "(:maxIntroduced IS NULL OR b.introduced <= :maxIntroduced)")
    List<Brand> findByFilter(@Param("minIntroduced") Integer minIntroduced,
                             @Param("maxIntroduced") Integer maxIntroduced);
    Optional<Brand> findFirstByIdLessThanOrderByIdDesc(int id);

    Optional<Brand> findFirstByOrderByIdDesc();

    Optional<Brand> findFirstByIdGreaterThanOrderByIdAsc(int id);

    Optional<Brand> findFirstByOrderByIdAsc();


}
