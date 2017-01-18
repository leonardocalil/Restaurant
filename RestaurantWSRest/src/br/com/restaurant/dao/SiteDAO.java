package br.com.restaurant.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.restaurant.model.SiteModel;
import br.com.restaurant.util.DBConnection;

public class SiteDAO extends AbstractDAO<SiteModel> {
	
	public List<SiteModel> getAll() {
		return getAll("deleted = 0 ");
	}
	
	public List<SiteModel> getAll(String filter) {
		List<SiteModel> results = new ArrayList<SiteModel>();
		DBConnection db = new DBConnection();
		ResultSet rs = null;
		
		try {
			String sql = "SELECT id, name  "
					+ "FROM restaurant.site ";
			if(filter != null && filter.length() > 0) {
				sql += "WHERE "+filter; 
			}
			rs = db.ExecuteQuery(sql);
			while(rs.next()) {
				SiteModel model = new SiteModel();
				model.setId(rs.getInt("id"));
				model.setName(rs.getString("name"));
				
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
	public SiteModel get(String id) {
		
		List<SiteModel> result =  getAll("id = "+id);
		if(result.size() > 0) {
			return result.get(0);
		}
		return null;

	}
	
	public boolean save(SiteModel model) {
		
		String sql = "insert into restaurant.site (id,name) "
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
		return result;
	}
	public boolean update(SiteModel model) {
		String sql = "update restaurant.site set name='"+model.getName()+"' "
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
		String sql = "update restaurant.site set deleted=1 "
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
