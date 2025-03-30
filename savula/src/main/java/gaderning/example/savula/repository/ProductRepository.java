package gaderning.example.savula.repository;
import gaderning.example.savula.model.Product;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // JPQL Query to find products by category (Paginated)
    @Query("SELECT p FROM Product p WHERE p.category = ?1")
    Page<Product> findByCategory(String category, Pageable pageable);

    // JPQL Query to find products with price greater than a value (Paginated)
    @Query("SELECT p FROM Product p WHERE p.price > ?1")
    Page<Product> findExpensiveProducts(Double price, Pageable pageable);

     @Query("SELECT u from Product u where u.price<= :price")
   public   List<Product> findLesserprice(@Param("price")int price);

}