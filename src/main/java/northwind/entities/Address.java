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
@Table(name = "address", catalog = "northwind",schema="northwind")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="business_phone")
	private String phone;
	private String address;
	private String city;
	@Column(name="state_province")
	private String state;
	@Column(name="zip_postal_code")
	private String zip;
	@Column(name="country_region")
	private String country;
}
