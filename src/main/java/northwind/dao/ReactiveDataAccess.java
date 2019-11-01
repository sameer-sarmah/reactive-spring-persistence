package northwind.dao;

import java.util.List;

import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@Service
public class ReactiveDataAccess implements IReactiveDataAccess {

	@Autowired
	private IDataAccess dataAccess;

	@Autowired
	private Scheduler scheduler;
	
	@Override
	public Mono<Pair<Integer, Double>> getExpensiveOrders() {
		return Mono.fromCallable(()->{
			return dataAccess.getExpensiveOrders();
		}).subscribeOn(scheduler);
	}
	

}
