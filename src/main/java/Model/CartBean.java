package Model;

import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name = "cart")
public class CartBean {
	@Id
	private int id;
	@OneToMany(mappedBy = "cartBean")
	private ArrayList<CartItemBean> alCartItems = new ArrayList<CartItemBean>();
	private double dblOrderTotal;

	public int getLineItemCount() {
		return alCartItems.size();
	}

	public void deleteCartItem(String strItemIndex) {
		int iItemIndex = 0;
		try {
			iItemIndex = Integer.parseInt(strItemIndex);
			alCartItems.remove(iItemIndex);
			calculateOrderTotal();
		} catch (NumberFormatException nfe) {
			System.out.println("Error while deleting cart item: " + nfe.getMessage());
			nfe.printStackTrace();
		}
	}

	public void updateCartItem(String strItemIndex, String strQuantity) {
		double dblTotalCost = 0.0;
		double dblUnitCost = 0.0;
		int iQuantity = 0;
		int iItemIndex = 0;
		CartItemBean cartItem = null;
		try {
			iItemIndex = Integer.parseInt(strItemIndex);
			iQuantity = Integer.parseInt(strQuantity);
			if (iQuantity > 0) {
				cartItem = (CartItemBean) alCartItems.get(iItemIndex);
				dblUnitCost = cartItem.getDblUnitCost();
				dblTotalCost = dblUnitCost * iQuantity;
				cartItem.setiQuantity(iQuantity);
				cartItem.setDblTotalCost(dblTotalCost);
				calculateOrderTotal();
			}
		} catch (NumberFormatException nfe) {
			System.out.println("Error while updating cart: " + nfe.getMessage());
			nfe.printStackTrace();
		}

	}

	public void addCartItem(String strModelNo, String strDescription, String strUnitCost, String strQuantity) {
		double dblTotalCost = 0.0;
		double dblUnitCost = 0.0;
		int iQuantity = 0;
		CartItemBean cartItem = new CartItemBean();
		try {
			dblUnitCost = Double.parseDouble(strUnitCost);
			iQuantity = Integer.parseInt(strQuantity);
			if (iQuantity > 0) {
				dblTotalCost = dblUnitCost * iQuantity;
				cartItem.setStrPartNumber(strModelNo);
				cartItem.setStrModelDescription(strDescription);
				cartItem.setDblUnitCost(dblUnitCost);
				cartItem.setiQuantity(iQuantity);
				cartItem.setDblTotalCost(dblTotalCost);
				alCartItems.add(cartItem);
				calculateOrderTotal();
			}
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
		}

	}

	public void addCartItem(CartItemBean cartItem) {
		alCartItems.add(cartItem);
	}

	public CartItemBean getCartItem(int iItemIndex) {
		CartItemBean cartItem = null;
		if (alCartItems.size() > iItemIndex) {
			cartItem = (CartItemBean) alCartItems.get(iItemIndex);
		}
		return cartItem;
	}

	public ArrayList<CartItemBean> getCartItems() {
		return alCartItems;
	}

	public void setCartItems(ArrayList<CartItemBean> alCartItems) {
		this.alCartItems = alCartItems;
	}

	public double getOrderTotal() {
		return dblOrderTotal;
	}

	public void setOrderTotal(double dblOrderTotal) {
		this.dblOrderTotal = dblOrderTotal;
	}

	public void calculateOrderTotal() {
		double dblTotal = 0;
		for (int counter = 0; counter < alCartItems.size(); counter++) {
			CartItemBean cartItem = (CartItemBean) alCartItems.get(counter);
			dblTotal += cartItem.getDblTotalCost();
		}
		setOrderTotal(dblTotal);
	}
}
