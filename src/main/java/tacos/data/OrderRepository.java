package tacos.data;

import org.springframework.data.repository.Repository;
import tacos.Order;

public interface OrderRepository extends Repository<Order, Long> {
  Order save(Order order);
}
