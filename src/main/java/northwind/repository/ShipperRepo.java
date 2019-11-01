package northwind.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import northwind.entities.Shippers;

public interface ShipperRepo  extends JpaRepository<Shippers, Integer>{

}
