package northwind.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import northwind.entities.Product;

public interface ProductRepo  extends JpaRepository<Product, Integer>{

}
