package br.com.restaurant.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.restaurant.model.StatusModel;
import br.com.restaurant.util.DBConnection;

public class StatusDAO extends AbstractDAO<StatusModel> {
	
	public List<StatusModel> getAll() {
		return getAll("deleted = 0 ");
	}
	
	public List<StatusModel> getAll(String filter) {
		List<StatusModel> results = new ArrayList<StatusModel>();
		DBConnection db = new DBConnection();
		ResultSet rs = null;
		
		try {
			String sql = "SELECT id, description  "
					+ "FROM restaurant.status ";
			if(filter != null && filter.length() > 0) {
				sql += "WHERE "+filter; 
			}
			rs = db.ExecuteQuery(sql);
			while(rs.next()) {
				StatusModel model = new StatusModel();
				model.setId(rs.getInt("id"));
				model.setDescription(rs.getString("description"));
				
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
	public StatusModel get(String id) {
		
		List<StatusModel> result =  getAll("id = "+id);
		if(result.size() > 0) {
			return result.get(0);
		}
		return null;

	}
	
	public boolean save(StatusModel model) {
		
		String sql = "insert into restaurant.status (id,description) "
				+ "values(nextval('status_seq'),'"+model.getDescription()+"')";
		
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
		return result;
	}
	public boolean update(StatusModel model) {
		String sql = "update restaurant.status set description='"+model.getDescription()+"' "
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
		return result;
	}
	public boolean delete(String id) {
		String sql = "update restaurant.status set deleted=1 "
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
		return result;
	}
}
