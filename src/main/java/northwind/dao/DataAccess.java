package northwind.dao;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataAccess implements IDataAccess {

	@Autowired
	private EntityManager entityManager;
	
	
	/*
	 * Most expensive placed order
	 * 
	 * select o.id,SUM(it.quantity*it.unit_price) as total from "northwind"."product" p inner join "northwind"."order_items" it 
	 * on p.id=it.product_id inner join "northwind"."order" o on o.id=it.order_id group by o.id order by total desc;
	 * 
	 */
	public Pair<Integer,Double> getExpensiveOrders(){
		Pair<Integer,Double> emptyPair = new Pair<Integer,Double>(0, 0.0);
		Query query =  entityManager.createNativeQuery("select o.id,SUM(it.quantity*it.unit_price) as total from \"northwind\".\"product\" p inner join \"northwind\".\"order_items\" it on p.id=it.product_id inner join \"northwind\".\"order\" o on o.id=it.order_id group by o.id order by total desc;");
		List<Object[]> rows = query.getResultList();
		Pair<Integer,Double> mostExpensive = rows.stream().map((row)->{
			if(row instanceof Object[]) {
				Object[] rowAsArr = (Object[])row;
				if(rowAsArr.length == 2) {
					Integer orderId = (Integer)rowAsArr[0];
					Double total = (Double)rowAsArr[1];
					return new Pair<Integer,Double>(orderId, total);
				}
			}
			return emptyPair;
		}).findFirst().orElseGet(()->emptyPair);
		return mostExpensive;
	}

/*
 *  Products which have generated most revenue
 *   select prod.product_name,SUM(it.quantity*it.unit_price) as total from "northwind"."product" prod inner join "northwind"."order_items" it 
     on prod.id=it.product_id inner join "northwind"."order" ord on ord.id=it.order_id group by prod.product_name order by total desc;
 * 
 * */

	
	
/*
 * Most valuable customers 
 *  select cus.first_name,cus.last_name,SUM(it.quantity*it.unit_price) as total from "northwind"."customer" cus inner join "northwind"."order" o 
 on cus.id=o.customer_id inner join "northwind"."order_items" it on o.id=it.order_id group by cus.id,cus.first_name,cus.last_name order by total desc;
 * */	
	
	
/*
 * Employee who have generated most revenue
 * 
 *  select emp.first_name,emp.last_name,SUM(it.quantity*it.unit_price) as total from "northwind"."employees" emp inner join "northwind"."order" o 
    on emp.id=o.employee_id inner join "northwind"."order_items" it on o.id=it.order_id group by emp.id,emp.first_name,emp.last_name order by total desc;


 * */	
	
}
