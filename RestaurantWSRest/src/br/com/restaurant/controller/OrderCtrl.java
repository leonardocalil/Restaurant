package br.com.restaurant.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.restaurant.dao.OrderDAO;
import br.com.restaurant.model.OrderModel;

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
	
	public static boolean save(OrderModel model) {
		OrderDAO dao = new OrderDAO();
		if(model.getId() != 0) {
			return dao.update(model) > 0 ? true : false;
		} else {
			return dao.save(model) > 0 ? true : false;
		}
		
	}
	public static boolean delete(String id) {
		return new OrderDAO().delete(id);
	}
	public static boolean updateStatus(String id,String status) {
		return new OrderDAO().updateStatus(id, status);
	}
	
}
