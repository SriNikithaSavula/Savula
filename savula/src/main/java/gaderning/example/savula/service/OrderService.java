package gaderning.example.savula.service;

import gaderning.example.savula.model.Order;
import gaderning.example.savula.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    // ✅ PUT method to update an order
    public Optional<Order> updateOrder(Long id, Order updatedOrder) {
        return orderRepository.findById(id).map(existingOrder -> {
            existingOrder.setCustomerName(updatedOrder.getCustomerName());
            existingOrder.setAmount(updatedOrder.getAmount());
            existingOrder.setOrderDate(updatedOrder.getOrderDate());
            existingOrder.setStatus(updatedOrder.getStatus());
            return orderRepository.save(existingOrder);
        });
    }
}
