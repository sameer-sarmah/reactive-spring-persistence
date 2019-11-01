package northwind.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import northwind.entities.Employees;

public interface EmployeesRepo  extends JpaRepository<Employees, Integer>{

}
