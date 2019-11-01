package northwind.dao;

import org.javatuples.Pair;

public interface IDataAccess {
	Pair<Integer,Double> getExpensiveOrders();
}
