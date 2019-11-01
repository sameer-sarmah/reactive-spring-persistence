package northwind.repository;

import org.springframework.stereotype.Component;

import northwind.entities.Customer;
import reactor.core.scheduler.Scheduler;

@Component
public class ReactiveCustomerRepo extends ReactiveCrudRepositoryAdapter<Customer,Integer,CustomerRepo> {

	ReactiveCustomerRepo(Scheduler scheduler,CustomerRepo repo){
		this.scheduler=scheduler;
		this.repository=repo;
	}
}
