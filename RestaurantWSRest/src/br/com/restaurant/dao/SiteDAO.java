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
			String sql = "SELECT id, name, document,address_name,address_number,address_complement,zip_code,phone,email,physical_store  "
					+ "FROM restaurant.site ";
			if(filter != null && filter.length() > 0) {
				sql += "WHERE "+filter; 
			}
			rs = db.ExecuteQuery(sql);
			while(rs.next()) {
				SiteModel model = new SiteModel();
				model.setId(rs.getInt("id"));
				model.setName(rs.getString("name"));
				model.setDocument(rs.getString("document"));
				model.setAddress_name(rs.getString("address_name"));
				model.setAddress_number(rs.getInt("address_number"));
				model.setAddress_complement(rs.getString("address_complement"));
				model.setZip_code(rs.getString("zip_code"));
				model.setPhone(rs.getString("phone"));
				model.setEmail(rs.getString("email"));
				model.setPhysical_store(rs.getInt("physical_store"));
				
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
		
		String sql = "insert into restaurant.site (id, name, document,address_name,address_number,address_complement,zip_code,phone,email,physical_store) "
				+ "values(nextval('site_seq'),'"+model.getName()+"',"
				+ (model.getDocument() != null && model.getDocument().length() > 0 ? "'"+model.getDocument()+"'" : "NULL")+","
				+ (model.getAddress_name() != null && model.getAddress_name().length() > 0? "'"+model.getAddress_name()+"'": "NULL")+","+model.getAddress_number()+","
				+ (model.getAddress_complement() != null && model.getAddress_complement().length() > 0? "'"+model.getAddress_complement()+"'": "NULL")+","
				+ (model.getZip_code() != null && model.getZip_code().length() > 0? "'"+model.getZip_code()+"'": "NULL")+","
				+ (model.getPhone() != null && model.getPhone().length() > 0? "'"+model.getPhone()+"'": "NULL")+","
				+ (model.getEmail() != null && model.getEmail().length() > 0? "'"+model.getEmail()+"'": "NULL")+","+model.getPhysical_store()+")";
		
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
		String sql = "update restaurant.site set name='"+model.getName()+"',"
				+ "document="+(model.getDocument() != null && model.getDocument().length() > 0 ? "'"+model.getDocument()+"'" : "NULL")+","
				+ "address_name="+(model.getAddress_name() != null && model.getAddress_name().length() > 0 ? "'"+model.getAddress_name()+"'" : "NULL")+","
				+ "address_number="+model.getAddress_number()+","
				+ "address_complement="+(model.getAddress_complement() != null && model.getAddress_complement().length() > 0 ? "'"+model.getAddress_complement()+"'" : "NULL")+","
				+ "zip_code="+(model.getZip_code() != null && model.getZip_code().length() > 0 ? "'"+model.getZip_code()+"'" : "NULL")+","
				+ "phone="+(model.getPhone() != null && model.getPhone().length() > 0 ? "'"+model.getPhone()+"'" : "NULL")+","
				+ "email="+(model.getEmail() != null && model.getEmail().length() > 0 ? "'"+model.getEmail()+"'" : "NULL")+","
				+ "physical_store="+model.getPhysical_store()+" "
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
