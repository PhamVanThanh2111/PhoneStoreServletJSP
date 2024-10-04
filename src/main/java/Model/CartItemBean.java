package Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cartItem")
public class CartItemBean {
	@Id
	@Column(name = "model")
	private String strPartNumber;
	
	@OneToOne
	@JoinColumn(name = "product_id", unique = true, nullable = false)
	private Product product;
	
	@Column(name = "description")
	private String strModelDescription;
	@Column(name = "unicost")
	private double dblUnitCost;
	@Column(name = "quantity")
	private int iQuantity;
	@Column(name = "totalcost")
	private double dblTotalCost;
	
	@ManyToOne
	@JoinColumn(name = "cart_id")
	private CartBean cartBean;

	public CartItemBean(String strPartNumber, String strModelDescription, double dblUnitCost, int iQuantity,
			double dblTotalCost) {
		super();
		this.strPartNumber = strPartNumber;
		this.strModelDescription = strModelDescription;
		this.dblUnitCost = dblUnitCost;
		this.iQuantity = iQuantity;
		this.dblTotalCost = dblTotalCost;
	}

	public CartItemBean() {
		super();
	}

	public String getStrPartNumber() {
		return strPartNumber;
	}

	public void setStrPartNumber(String strPartNumber) {
		this.strPartNumber = strPartNumber;
	}

	public String getStrModelDescription() {
		return strModelDescription;
	}

	public void setStrModelDescription(String strModelDescription) {
		this.strModelDescription = strModelDescription;
	}

	public double getDblUnitCost() {
		return dblUnitCost;
	}

	public void setDblUnitCost(double dblUnitCost) {
		this.dblUnitCost = dblUnitCost;
	}

	public int getiQuantity() {
		return iQuantity;
	}

	public void setiQuantity(int iQuantity) {
		this.iQuantity = iQuantity;
	}

	public double getDblTotalCost() {
		return dblTotalCost;
	}

	public void setDblTotalCost(double dblTotalCost) {
		this.dblTotalCost = dblTotalCost;
	}

	@Override
	public String toString() {
		return "CartItemBean [strPartNumber=" + strPartNumber + ", strModelDescription=" + strModelDescription
				+ ", dblUnitCost=" + dblUnitCost + ", iQuantity=" + iQuantity + ", dblTotalCost=" + dblTotalCost + "]";
	}
}
