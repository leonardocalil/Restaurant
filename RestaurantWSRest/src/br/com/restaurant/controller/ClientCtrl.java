package br.com.restaurant.controller;

import java.util.List;

import org.json.JSONObject;

import br.com.restaurant.dao.ClientDAO;
import br.com.restaurant.model.ClientModel;

public class ClientCtrl {
	
	public static List<ClientModel> getAll() {
		return new ClientDAO().getAll();
	}
	public static ClientModel get(String id) {
		return new ClientDAO().get(id);
	}
	public static ClientModel validateUser(String user_password) {
		JSONObject obj = new JSONObject(user_password);
		ClientDAO dao = new ClientDAO();
		List<ClientModel> result = dao.getAll("lower(login) = '"+obj.getString("user").toLowerCase()+"' AND password = '"+obj.getString("password")+"' AND deleted=0");
		if(result.size() > 0) {
			return result.get(0);
		}
		return null;
	}
	
	public static int existsLogin(String login) {
		return new ClientDAO().existsLogin(login);
	}
	public static boolean delete(String id) {
		return new ClientDAO().delete(id);
	}
	public static int save(ClientModel model) {
		ClientDAO dao = new ClientDAO();
		if(model.getId() == 0) {
			return dao.save(model);
		} else {
			return dao.update(model);
		}
		
	}
	
	
}
