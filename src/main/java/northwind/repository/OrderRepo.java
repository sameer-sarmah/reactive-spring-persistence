package northwind.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import northwind.entities.Order;

public interface OrderRepo  extends JpaRepository<Order, Integer>{

}
