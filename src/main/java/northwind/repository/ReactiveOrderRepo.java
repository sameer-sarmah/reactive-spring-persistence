package northwind.repository;

import northwind.entities.Order;
import reactor.core.scheduler.Scheduler;

public class ReactiveOrderRepo extends ReactiveCrudRepositoryAdapter<Order,Integer,OrderRepo>{
	ReactiveOrderRepo(Scheduler scheduler,OrderRepo repo){
		this.scheduler=scheduler;
		this.repository=repo;
	}
}
