package ray1024.blps.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ray1024.blps.model.entity.ItemStack;
import ray1024.blps.model.entity.Order;
import ray1024.blps.model.entity.User;
import ray1024.blps.repository.OrderRepository;

import java.util.Set;

@Service
@AllArgsConstructor
public class ClientService {

    private OrderRepository orderRepository;

    @Transactional
    public Order order(@NonNull User user, @NonNull Set<ItemStack> itemStacks) {
        Order order = Order.builder()
                .id(0L)
                .client(user)
                .packer(null)
                .courier(null)
                .status(Order.Status.ORDERED)
                .itemStacks(itemStacks)
                .build();
        return orderRepository.save(order);
    }
}
