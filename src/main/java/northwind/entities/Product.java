package northwind.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@Table(name = "product", catalog = "northwind",schema="northwind")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="product_name")
	private String name;
	private String description;
	@Column(name="standard_cost")
	private double standardCost;
	@Column(name="list_price")
	private double listPrice;
	@Column(name="quantity_per_unit")
	private String quantityPerUnit;
	private boolean discontinued;
	@Column(name="minimum_reorder_quantity")
	private int minimumReorderQuantity;
	private String category;
}
