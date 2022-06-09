package be.thomasmore.gin.repositories;

import be.thomasmore.gin.model.Recept;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReceptRepository extends CrudRepository<Recept,Integer> {
    @Query("SELECT r FROM Recept r WHERE " +
            "(:minPrice IS NULL OR :minPrice <= r.price) AND " +
            "(:maxPrice IS NULL OR r.price <= :maxPrice)")
    List<Recept> findByFilter(@Param("minPrice") Double minPrice,
                             @Param("maxPrice") Double maxPrice);
    @Query("SELECT AVG(r.price) from Recept r")
    int getAveragePrice();

    @Query(value = "SELECT sum(r.price) FROM Recept r")
    int getTotalPrice();
    Optional<Recept> findFirstByIdLessThanOrderByIdDesc(int id);

    Optional<Recept> findFirstByOrderByIdDesc();

    Optional<Recept> findFirstByIdGreaterThanOrderByIdAsc(int id);

    Optional<Recept> findFirstByOrderByIdAsc();

    Iterable<Recept> findByWater(boolean yes);

    Iterable<Recept> findBySugar(boolean yes);

    Optional<Recept> findById(Integer id);
}
