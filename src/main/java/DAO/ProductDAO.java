package DAO;

import java.sql.SQLException;
import java.util.List;

import Model.Product;

public interface ProductDAO {
	public List<Product> getAllProducts() throws SQLException;
	
	public Product addProduct(Product product) throws SQLException;
}
