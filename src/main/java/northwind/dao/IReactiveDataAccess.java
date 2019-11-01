package northwind.dao;

import org.javatuples.Pair;

import reactor.core.publisher.Mono;

public interface IReactiveDataAccess {
	Mono<Pair<Integer, Double>> getExpensiveOrders();
}
