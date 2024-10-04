package DAOImpl;

import java.sql.SQLException;
import java.util.List;

import DAO.ProductDAO;
import Model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class ProductDAOImpl implements ProductDAO {

	private EntityManager entityManager;
	public ProductDAOImpl(EntityManagerFactory entityManagerFactory) {
		this.entityManager = entityManagerFactory.createEntityManager();
	}
	
	@Override
	public List<Product> getAllProducts() throws SQLException {
		return entityManager.createQuery("from Product", Product.class).getResultList();
	}

	@Override
	public Product addProduct(Product product) throws SQLException {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(product);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if (entityManager.getTransaction() != null && entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
				return null;
			}
		}
		return product;
	}

}
