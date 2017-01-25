package br.com.restaurant.controller;

import java.util.List;

import br.com.restaurant.dao.SiteDAO;
import br.com.restaurant.model.SiteModel;

public class SiteCtrl {
	public static List<SiteModel> getAll() {
		return new SiteDAO().getAll();
	}
	
	
	
	public static SiteModel get(String id) {
		return new SiteDAO().get(id);
	}
	
	public static boolean save(SiteModel model) {
		SiteDAO dao = new SiteDAO();
		if(model.getId() != 0) {
			return dao.update(model) > 0 ? true : false;
		} else {
			return dao.save(model) > 0 ? true : false;
		}
		
	}
	public static boolean delete(String id) {
		return new SiteDAO().delete(id);
	}
	
}
