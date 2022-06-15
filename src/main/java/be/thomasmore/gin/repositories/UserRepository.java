package be.thomasmore.gin.repositories;

import be.thomasmore.gin.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Integer> {
    @Query("select u from User u where u.username=:name")
    Optional<User> findUserByUsername(String name);


}
