package InitData;

import java.sql.SQLException;

import DAOImpl.ProductDAOImpl;
import Model.Product;
import jakarta.persistence.Persistence;

public class Init {
	public static void main(String[] args) throws SQLException {
		Product sp1 = new Product();
		sp1.setId("PRO01");
		sp1.setModel("Nokia Lumia 520");
		sp1.setDescription("");
		sp1.setQuantity(10);
		sp1.setPrice(3000000);
		sp1.setImgURL("nokia-lumia-520.jpg");
		
		Product sp2 = new Product();
		sp2.setId("PRO02");
		sp2.setModel("BlackBerry Passport");
		sp2.setDescription("");
		sp2.setQuantity(10);
		sp2.setPrice(10000000);
		sp2.setImgURL("blackberry-passport.jpg");
		
		Product sp3 = new Product();
		sp3 = new Product();
		sp3.setId("PRO03");
		sp3.setModel("Sony Xperia Z5");
		sp3.setDescription("");
		sp3.setQuantity(10);
		sp3.setPrice(7000000);
		sp3.setImgURL("sony-xperia-z5.jpg");
		
		Product sp4 = new Product();
		sp4.setId("PRO04");
		sp4.setModel("HTC One M9");
		sp4.setDescription("");
		sp4.setQuantity(10);
		sp4.setPrice(8000000);
		sp4.setImgURL("htc-one-m9.jpg");
		
		Product sp5 = new Product();
		sp5.setId("PRO05");
		sp5.setModel("Samsung Galaxy Note 5");
		sp5.setDescription("");
		sp5.setQuantity(10);
		sp5.setPrice(12000000);
		sp5.setImgURL("samsung-galaxy-note-5.jpg");
		
		
		ProductDAOImpl productDAO = new ProductDAOImpl(Persistence.createEntityManagerFactory("phoneStore"));
		productDAO.addProduct(sp1);
		productDAO.addProduct(sp2);
		productDAO.addProduct(sp3);
		productDAO.addProduct(sp4);
		productDAO.addProduct(sp5);
		
	}
}
