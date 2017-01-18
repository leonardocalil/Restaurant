package br.com.restaurant.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.restaurant.model.OrderModel;
import br.com.restaurant.model.OrderProductModel;
import br.com.restaurant.util.DBConnection;

public class OrderDAO extends AbstractDAO<OrderModel> {
	
	public List<OrderModel> getAll() {
		return getAll(null);
	}
	
	public List<OrderProductModel> getOrderDetail(String orderId) {
		List<OrderProductModel> results = new ArrayList<OrderProductModel>();
		DBConnection db = new DBConnection();
		ResultSet rs = null;
		
		try {
			String sql = "SELECT id, order_id, product_id, quantity,total_price,total_final_price "
					+ "FROM restaurant.order_products "
					+ "WHERE order_id = "+orderId;					
			
			rs = db.ExecuteQuery(sql);
			while(rs.next()) {
				OrderProductModel model = new OrderProductModel();
				model.setId(rs.getInt("id"));
				model.setOrder_id(rs.getInt("order_id"));
				model.setProduct(new ProductDAO().get(rs.getString("product_id")));
				model.setQuantity(rs.getInt("quantity"));
				model.setTotal_price(rs.getString("total_price"));
				model.setTotal_final_price(rs.getString("total_final_price"));
				
				results.add(model);
				
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.finalize();
		}
		return results;
	}
	
	public List<OrderModel> getAll(String filter) {
		List<OrderModel> results = new ArrayList<OrderModel>();
		DBConnection db = new DBConnection();
		ResultSet rs = null;
		
		try {
			String sql = "SELECT id , client_id, status, site_id, to_char(o.datetime,'dd/mm/yyyy hh24:mi:ss') datetime "
					+ "FROM restaurant.order ";					
			if(filter != null && filter.length() > 0) {
				sql += "WHERE "+filter; 
			}
			rs = db.ExecuteQuery(sql);
			while(rs.next()) {
				OrderModel model = new OrderModel();
				model.setId(rs.getInt("id"));
				model.setDatetime(rs.getString("datetime"));
				model.setClient(new ClientDAO().get(rs.getString("client_id")));
				model.setSite(new SiteDAO().get(rs.getString("site_id")));
				model.setStatus(new StatusDAO().get(rs.getString("status")));
				
				model.setProducts(this.getOrderDetail(rs.getString("id")));
				
				results.add(model);
				
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.finalize();
		}
		return results;
	}
	

	@Override
	public OrderModel get(String id) {
		
		List<OrderModel> result =  getAll("id = "+id);
		if(result.size() > 0) {
			return result.get(0);
		}
		return null;

	}
	
	public boolean save(OrderModel model) {
		
		/*String sql = "insert into restaurant.site (id,name) "
				+ "values(nextval('site_seq'),'"+model.getName()+"')";
		
		DBConnection db = new DBConnection();
		
		boolean result = true;
		
		try {
			result = db.ExecuteSql(sql);				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = false;
		} finally {
			db.finalize();
		}
		return result;*/
		return false;
	}
	public boolean update(OrderModel model) {
		/*String sql = "update restaurant.site set name='"+model.getName()+"' "
				+ "where id = "+model.getId();
		
		DBConnection db = new DBConnection();
		
		boolean result = true;
		
		try {
			result = db.ExecuteSql(sql);				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = false;
		} finally {
			db.finalize();
		}
		return result;*/
		return false;
	}
	public boolean delete(String id) {
		/*String sql = "update restaurant.site set deleted=1 "
				+ "where id = "+id;
		
		DBConnection db = new DBConnection();
		
		boolean result = true;
		
		try {
			result = db.ExecuteSql(sql);				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = false;
		} finally {
			db.finalize();
		}
		return result;*/
		return false;
	}

	
}
