package northwind.repository;

import northwind.entities.Product;
import reactor.core.scheduler.Scheduler;

public class ReactiveProductRepo extends ReactiveCrudRepositoryAdapter<Product,Integer,ProductRepo>{
	ReactiveProductRepo(Scheduler scheduler,ProductRepo repo){
		this.scheduler=scheduler;
		this.repository=repo;
	}
}
