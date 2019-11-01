package northwind.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "order", catalog = "northwind",schema="northwind")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne
	@JoinColumn(name="employee_id")
	private Employees employees;
	
	@OneToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@OneToOne
	@JoinColumn(name="shipped_address")
	private Address address;
	
	@OneToOne
	@JoinColumn(name="shipper_id")
	private Shippers shippers;
	
	@OneToOne
	@JoinColumn(name="status_id")
	private OrderStatus orderStatus;
	

	@Column(name="order_date")
	private LocalDateTime orderDate;
	
	@Column(name="shipped_date")
	private LocalDateTime shippedDate;
	
	@Column(name="paid_date")
	private LocalDateTime paidDate;
	
	
	@Column(name="shipping_fee")
	private double shippingFee;
	
	private double taxes;
	
	@Column(name="payment_type")
	private String paymentType;
}
