package northwind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import northwind.entities.Customer;


@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer>{

}