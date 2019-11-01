import java.util.List;

import org.javatuples.Pair;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import northwind.config.PostgresConfig;
import northwind.dao.DataAccess;
import northwind.dao.IReactiveDataAccess;
import northwind.dao.ReactiveDataAccess;
import northwind.entities.Customer;
import northwind.repository.CustomerRepo;
import northwind.repository.ReactiveCustomerRepo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Driver {

	public static void main(String[] args) throws InterruptedException {
		//testReactiveRepository();
		//testReactiveDAO();
		testR2DBCDAO();
		Thread.sleep(1000000);
	}

	static void testDAO() {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(PostgresConfig.class);
		DataAccess dataAccess = (DataAccess) ctx.getBean("dataAccess", DataAccess.class);
		Pair<Integer, Double> mostExpensiveOrder = dataAccess.getExpensiveOrders();
		System.out.println(
				"order id is " + mostExpensiveOrder.getValue0() + " and total is " + mostExpensiveOrder.getValue1());
	}

	static void testReactiveDAO() {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(PostgresConfig.class);
		ReactiveDataAccess dataAccess = (ReactiveDataAccess) ctx.getBean("reactiveDataAccess",
				ReactiveDataAccess.class);
		Mono<Pair<Integer, Double>> rows = dataAccess.getExpensiveOrders();
		rows.subscribe((Pair<Integer, Double> mostExpensiveOrder) -> {
			System.out.println("order id is " + mostExpensiveOrder.getValue0() + " and total is "
					+ mostExpensiveOrder.getValue1());
		});
	}
	
	static void testR2DBCDAO() {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(PostgresConfig.class);
		IReactiveDataAccess dataAccess = (IReactiveDataAccess) ctx.getBean("r2DBCDattaAccess",
				IReactiveDataAccess.class);
		Mono<Pair<Integer, Double>> rows = dataAccess.getExpensiveOrders();
		rows.subscribe((Pair<Integer, Double> mostExpensiveOrder) -> {
			System.out.println("order id is " + mostExpensiveOrder.getValue0() + " and total is "
					+ mostExpensiveOrder.getValue1());
		});
	}

	static void testJPARepository() {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(PostgresConfig.class);
		CustomerRepo customerRepo = (CustomerRepo) ctx.getBean("customerRepo", CustomerRepo.class);
		List<Customer> customers = customerRepo.findAll();

	}

	static void testReactiveRepository() {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(PostgresConfig.class);
		ReactiveCustomerRepo reactiveCustomerRepo = (ReactiveCustomerRepo) ctx.getBean("reactiveCustomerRepo",
				ReactiveCustomerRepo.class);
		Flux<Customer> customerStream = reactiveCustomerRepo.findAll();
		customerStream.take(5).subscribe((customer) -> System.out.println(customer.getFirstName()));
	}
}
