package northwind.dao;

import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.function.DatabaseClient;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class R2DBCDattaAccess implements IReactiveDataAccess{

	@Autowired
	private DatabaseClient databaseClient ;
	
	@Override
	public Mono<Pair<Integer, Double>> getExpensiveOrders() {
		Mono<Pair<Integer,Double>> mono = databaseClient.execute()
		        .sql("select o.id,SUM(it.quantity*it.unit_price) as total from \"northwind\".\"product\" p inner join \"northwind\".\"order_items\" it on p.id=it.product_id inner join \"northwind\".\"order\" o on o.id=it.order_id group by o.id order by total desc;")
		        .map((row,rowMetadata)->{
		        	Integer orderId = row.get("id", Integer.class);
		        	Double total = row.get("total", Double.class);
		        	System.out.println("order id is " + orderId + " and total is "+ total);
		        	return new Pair<Integer,Double>(orderId, total);
		        })
		        .first();
		return mono;
	}



}
