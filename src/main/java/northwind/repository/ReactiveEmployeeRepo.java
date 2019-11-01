package northwind.repository;

import northwind.entities.Employees;
import reactor.core.scheduler.Scheduler;

public class ReactiveEmployeeRepo extends ReactiveCrudRepositoryAdapter<Employees,Integer,EmployeesRepo>{
	ReactiveEmployeeRepo(Scheduler scheduler,EmployeesRepo repo){
		this.scheduler=scheduler;
		this.repository=repo;
	}
}
