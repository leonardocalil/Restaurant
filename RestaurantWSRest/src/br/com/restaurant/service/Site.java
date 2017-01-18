package br.com.restaurant.service;



import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.restaurant.controller.RoleCtrl;
import br.com.restaurant.controller.SiteCtrl;
import br.com.restaurant.model.SiteModel;

@Path("/site")
public class Site {
	
		
	@GET
	@Path("alive")
	@Produces(MediaType.TEXT_PLAIN)
	public String alive() {
		return "Sim, estou vivo!!";
	}
	
	@GET
	@Path("get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public SiteModel get(@PathParam("id") String id) {
		
		return SiteCtrl.get(id);
	}
	
	@GET
	@Path("getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SiteModel> getAll() {
		
		return SiteCtrl.getAll();
	}
	
	@POST
	@Path("save")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean save(SiteModel model) {
		return SiteCtrl.save(model);
	}

	@GET
	@Path("delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean delete(@PathParam("id") String id) {
		
		return RoleCtrl.delete(id);
	}
	
	
}
