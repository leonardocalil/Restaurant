package br.com.restaurant.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.restaurant.dao.ProductDAO;
import br.com.restaurant.model.ProductModel;
import br.com.restaurant.model.ProductTypeModel;

public class ProductCtrl {
	public static List<ProductModel> getAll() {
		return new ProductDAO().getAll();  
		
	}
	
	public static List<ProductModel> getAllTypes() {
		return new ProductDAO().getAll();  
		
	}
	
	public static String getAllJson() {
		List<ProductModel> result = new ProductDAO().getAll();  
		JSONArray array = new JSONArray();
		for(ProductModel model : result) {
			JSONObject obj = new JSONObject(model);
			obj.put("product_type", model.getProductType().getDescription());
			array.put(obj);
		}
		return array.toString();
	}
	
	public static ProductModel get(String id) {
		return new ProductDAO().get(id);
	}
	
	public static boolean save(String jsonString) {
		
		JSONObject json = new JSONObject(jsonString);
		ProductDAO dao = new ProductDAO();
		ProductModel model = new ProductModel();
		
		model.setId(json.getInt("id"));
		model.setName(json.getString("name"));
		model.setDescription(json.getString("description"));
		
		
		model.setCost_price(json.getString("cost_price").replace(",", "."));
		
		model.setSale_price(json.getString("sale_price").replace(",", "."));
		
		ProductTypeModel type = new ProductTypeModel();
		type.setId(json.getJSONObject("productType").getInt("id"));
		type.setDescription(json.getJSONObject("productType").getString("description"));
		
		model.setProductType(type);
		
		if(model.getId() != 0) {
			return dao.update(model) > 0 ? true : false;
		} else {
			return dao.save(model) > 0 ? true : false;
		}
		
	}
	public static boolean delete(String id) {
		return new ProductDAO().delete(id);
	}
	public static List<ProductModel> getByType(String typeId) {	
		return new ProductDAO().getAll("p.deleted = 0 AND tp.deleted = 0 and p.product_type_id = "+typeId);  
		
	}
}
	