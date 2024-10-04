package Controller;

import java.io.IOException;

import Model.CartBean;
import Model.CartItemBean;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class CartController
 */
@WebServlet(urlPatterns = "/CartController")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String strAction = request.getParameter("action");
		if (strAction != null && !strAction.equals("")) {
			if (strAction.equals("Add")) {
				addToCart(request);
			} else if (strAction.equals("Update")) {
				updateCart(request);
			} else if (strAction.equals("Delete")) {
				deleteCart(request);
			}
		}
		response.sendRedirect("ShoppingCart.jsp");
	}

	protected void deleteCart(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String strItemIndex = request.getParameter("itemIndex");
		CartBean cartBean = (CartBean) session.getAttribute("cart");
	    int itemIndex = Integer.parseInt(strItemIndex);
		if (cartBean != null) {
	        // Kiểm tra chỉ số hợp lệ trước khi xóa
	        if (itemIndex >= 0 && itemIndex < cartBean.getCartItems().size()) {
	            cartBean.deleteCartItem(strItemIndex);
	        } else {
	            System.out.println("Item index out of range: " + itemIndex);
	            System.out.println("Cart size: " + cartBean.getCartItems().size());
	        }
	    } else {
	        System.out.println("Cart is empty.");
	    }
	}

	protected void updateCart(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String strQuantity = request.getParameter("quantity");
		String strItemIndex = request.getParameter("itemIndex");
		
		CartBean cartBean = null;

		Object objCartBean = session.getAttribute("cart");
		if (objCartBean != null) {
			cartBean = (CartBean) objCartBean;
		} else {
			cartBean = new CartBean();
		}
		cartBean.updateCartItem(strItemIndex, strQuantity);
	}

	protected void addToCart(HttpServletRequest request) {
		HttpSession session = request.getSession();
		// Lấy các thông tin từ request
		String strDescription = request.getParameter("description");
		String strPrice = request.getParameter("price");
		String strQuantity = request.getParameter("quantity");
		String model = request.getParameter("model");
		
		// Lấy giỏ hàng từ session
		CartBean cartBean = null;
		Object objCartBean = session.getAttribute("cart");
		
		if (objCartBean != null) {
			cartBean = (CartBean) objCartBean;
		} else {
			cartBean = new CartBean();
			session.setAttribute("cart", cartBean);
		}
		
		// Kiểm tra nếu sản phẩm đã có trong giỏ hàng
	    boolean itemExists = false;
	    for (CartItemBean item : cartBean.getCartItems()) {
	        if (item.getStrPartNumber().equals(model)) {
	            // Nếu sản phẩm đã có, tăng số lượng lên
	            int newQuantity = item.getiQuantity() + Integer.parseInt(strQuantity);
	            item.setiQuantity(newQuantity);
	            item.setDblTotalCost(newQuantity * item.getDblUnitCost());  // Cập nhật lại tổng giá tiền
	            cartBean.calculateOrderTotal(); // Cập nhật tổng đơn hàng
	            itemExists = true;
	            break;
	        }
	    }
	    // Nếu sản phẩm chưa có trong giỏ, thêm vào giỏ hàng
	    if (!itemExists) {
			cartBean.addCartItem(model, strDescription, strPrice, strQuantity);
		}
	}
}
