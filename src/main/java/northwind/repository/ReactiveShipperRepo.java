package northwind.repository;

import northwind.entities.Shippers;
import reactor.core.scheduler.Scheduler;

public class ReactiveShipperRepo  extends ReactiveCrudRepositoryAdapter<Shippers,Integer,ShipperRepo> {
	ReactiveShipperRepo(Scheduler scheduler,ShipperRepo repo){
		this.scheduler=scheduler;
		this.repository=repo;
	}
}
