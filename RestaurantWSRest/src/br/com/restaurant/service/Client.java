package br.com.restaurant.service;



import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.restaurant.controller.ClientCtrl;
import br.com.restaurant.model.ClientModel;

@Path("/client")
public class Client {
	
		
	@GET
	@Path("alive")
	@Produces(MediaType.TEXT_PLAIN)
	public String alive() {
		return "Sim, estou vivo!!";
	}
	
	
	@POST
	@Path("save")
	@Produces(MediaType.APPLICATION_JSON)
	public int save(ClientModel model) {
		return ClientCtrl.save(model);
	}
	
	
	@GET
	@Path("get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ClientModel get(@PathParam("id") String id) {
		
		return ClientCtrl.get(id);
	}
	@POST
	@Path("validateUser")
	@Produces(MediaType.APPLICATION_JSON)
	public ClientModel validateUser(String user_password) {
		
		return ClientCtrl.validateUser(user_password);
	}
	@GET
	@Path("existsLogin/{login}")
	@Produces(MediaType.APPLICATION_JSON)
	public int existsLogin(@PathParam("login") String login) {
	
		return ClientCtrl.existsLogin(login);
	}
	@GET
	@Path("delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean delete(@PathParam("id") String id) {
		
		return ClientCtrl.delete(id);
	}

	@GET
	@Path("getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ClientModel> getAll() {
		
		return ClientCtrl.getAll();
	}
	
	
	
	
	
}
