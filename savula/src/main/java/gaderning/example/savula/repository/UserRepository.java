package gaderning.example.savula.repository;

import gaderning.example.savula.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Custom JPQL query to find users by name
    @Query("SELECT u FROM User u WHERE u.name LIKE %:name%")
    List<User> findByName(String name);

    // Pagination and Sorting
    Page<User> findAll(Pageable pageable);

    @Query("SELECT u from User u where u.age<= :age")
    List<User> findLesserAge(@Param("age")int age);
}