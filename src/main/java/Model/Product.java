package Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class Product {
	@Id
	private String id;
	private String model;
	private String description;
	private int quantity;
	private double price;
	private String imgURL;
	
//	@OneToOne(mappedBy = "product")
//	private CartItemBean cartItemBean;

	public Product(String id, String model, String description, int quantity, double price, String imgURL) {
		super();
		this.id = id;
		this.model = model;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
		this.imgURL = imgURL;
	}

	public Product() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImgURL() {
		return imgURL;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", model=" + model + ", description=" + description + ", quantity=" + quantity
				+ ", price=" + price + ", imgURL=" + imgURL + "]";
	}
}
