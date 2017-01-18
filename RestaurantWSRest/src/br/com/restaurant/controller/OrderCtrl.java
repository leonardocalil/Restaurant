package br.com.restaurant.controller;

import java.util.List;

import br.com.restaurant.dao.OrderDAO;
import br.com.restaurant.model.OrderModel;

public class OrderCtrl {
	public static List<OrderModel> getAll() {
		return new OrderDAO().getAll();
	}
	
	
	
	public static OrderModel get(String id) {
		return new OrderDAO().get(id);
	}
	
	public static boolean save(OrderModel model) {
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
	
}
