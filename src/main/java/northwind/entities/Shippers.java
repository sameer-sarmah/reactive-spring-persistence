package northwind.entities;

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
@Table(name = "shippers", catalog = "northwind",schema="northwind")
public class Shippers {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String company;
	@Column(name="web_page")
	private String webPage;
	@Column(name="email_address")
	private String email;
	
	@JoinColumn(name="address")
	@OneToOne
	private Address address;
}
