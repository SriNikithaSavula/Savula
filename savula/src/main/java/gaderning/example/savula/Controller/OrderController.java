package gaderning.example.savula.Controller;

import gaderning.example.savula.model.Order;
import gaderning.example.savula.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/Orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    // ✅ Create a new Order (POST)
    @PostMapping("/add")
    public Order createOrder(@RequestBody Order order) {
        return orderService.saveOrder(order);
    }

    // ✅ Get Orders with Pagination (GET)
    @GetMapping("/get")
    public Page<Order> getOrders(Pageable pageable) {
        return orderService.getAllOrders().stream()
                .collect(java.util.stream.Collectors.collectingAndThen(
                        java.util.stream.Collectors.toList(),
                        list -> new org.springframework.data.domain.PageImpl<>(list, pageable, list.size())
                ));
    }

    // ✅ Get Order by ID (GET)
    @GetMapping("/get/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Optional<Order> order = orderService.getOrderById(id);
        return order.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Update Order by ID (PUT)
    @PutMapping("/put/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order orderDetails) {
        return orderService.updateOrder(id, orderDetails)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Delete Order by ID (DELETE)
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
