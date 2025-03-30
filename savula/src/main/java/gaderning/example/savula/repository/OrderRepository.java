package gaderning.example.savula.repository;

import gaderning.example.savula.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
