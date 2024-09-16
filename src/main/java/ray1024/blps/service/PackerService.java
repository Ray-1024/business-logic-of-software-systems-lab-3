package ray1024.blps.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ray1024.blps.model.entity.Order;
import ray1024.blps.model.entity.User;
import ray1024.blps.repository.OrderRepository;

@Service
@AllArgsConstructor
public class PackerService {

    private OrderRepository orderRepository;

    @Transactional
    public Order findNewOrder(@NonNull User user) {
        if (orderRepository.findByPacker(user).isPresent())
            throw new IllegalStateException("Already have order in packing");
        Order order = orderRepository.findFirstByPackerIsNull().orElseThrow();
        order.setStatus(Order.Status.PACKING);
        order.setPacker(user);
        return orderRepository.save(order);
    }

    @Transactional
    public Order doneOrder(@NonNull User user) {
        if (orderRepository.findByPacker(user).isEmpty())
            throw new IllegalStateException("No orders to done");
        Order order = orderRepository.findByPacker(user).orElseThrow();
        order.setStatus(Order.Status.PACKED);
        return orderRepository.save(order);
    }
}
