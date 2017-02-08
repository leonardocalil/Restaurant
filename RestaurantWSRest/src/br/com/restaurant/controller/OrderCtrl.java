package br.com.restaurant.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.restaurant.dao.OrderDAO;
import br.com.restaurant.model.OrderModel;
import br.com.restaurant.model.OrderProductModel;

public class OrderCtrl {
	public static List<OrderModel> getAll() {
		return new OrderDAO().getAll();
	}
	public static List<OrderModel> getQueue(String siteId) {
		List<OrderModel> orders = new OrderDAO().getAll("status in (0,1) ");
		List<OrderModel> result = new ArrayList<OrderModel>(); 
		if(siteId!= null) {
			if(siteId.equals("0")) {
				result.addAll(orders);
			} else {
				for(OrderModel model : orders) {
					if(model.getSite().getId() == Integer.valueOf(siteId)) {
						result.add(model);
					}
				}
			}
		}
		return result; 
	}

	
	
	public static OrderModel get(String id) {
		return new OrderDAO().get(id);
	}
	
	public static int save(OrderModel model) {
		OrderDAO dao = new OrderDAO();
		if(model.getId() != 0) {
			return dao.update(model);
		} else {
			return dao.save(model);
		}
		
	}
	public static boolean delete(String id) {
		return new OrderDAO().delete(id);
	}
	public static boolean updateStatus(String id,String status) {
		return new OrderDAO().updateStatus(id, status);
	}
	public static int put(String cart) {

		JSONObject obj = new JSONObject(cart);
		JSONObject objClient = obj.getJSONObject("customer");
		JSONObject objSite = obj.getJSONObject("site");
		JSONArray objItems = obj.getJSONArray("items");
		
		OrderDAO dao = new OrderDAO();
		OrderModel model = new OrderModel();
		List<OrderProductModel> products = new ArrayList<OrderProductModel>();
		
		for(int ix = 0; ix < objItems.length(); ix++) {			
			JSONObject item = objItems.getJSONObject(ix);
			OrderProductModel pmodel = new OrderProductModel();
			pmodel.setProduct(ProductCtrl.get(item.getString("_id")));
			pmodel.setQuantity(item.getInt("_quantity"));
			pmodel.setTotal_price(String.valueOf(item.getDouble("_price") * item.getInt("_quantity")) );
			pmodel.setTotal_final_price(pmodel.getTotal_price());
			products.add(pmodel);
		}
		
		
		model.setClient(ClientCtrl.get(String.valueOf(objClient.getInt("id"))) );
		model.setSite(SiteCtrl.get(String.valueOf(objSite.getInt("id"))));

		
		if(!obj.get("tax").equals(JSONObject.NULL))
			model.setTax(obj.getString("tax"));
		if(!obj.get("shipping").equals(JSONObject.NULL))	
			model.setShipping(obj.getString("shipping"));
		
		model.setProducts(products);
		
		
		return dao.save(model);
		
	}
	
}
