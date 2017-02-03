package br.com.restaurant.service;



import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.restaurant.controller.OrderCtrl;
import br.com.restaurant.model.OrderModel;

@Path("/order")
public class Order {
	
		
	@GET
	@Path("alive")
	@Produces(MediaType.TEXT_PLAIN)
	public String alive() {
		return "Sim, estou vivo!!";
	}
	
	@GET
	@Path("get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public OrderModel get(@PathParam("id") String id) {
		
		return OrderCtrl.get(id);
	}
	
	@GET
	@Path("getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public List<OrderModel> getAll() {
		
		return OrderCtrl.getAll();
	}
	
	@POST
	@Path("save")
	@Produces(MediaType.APPLICATION_JSON)
	public int save(OrderModel model) {
		return OrderCtrl.save(model);
	}

	@GET
	@Path("delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean delete(@PathParam("id") String id) {
		
		return OrderCtrl.delete(id);
	}
	@GET
	@Path("updateStatus/{id}/{status}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean udpateStatus(@PathParam("id") String id,
					      @PathParam("status") String status) {
		
		return OrderCtrl.updateStatus(id,status);
	}
	
	@GET
	@Path("getQueue/{siteId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<OrderModel> getQueue(@PathParam("siteId") String siteId) {
		
		return OrderCtrl.getQueue(siteId);
	}
	@POST
	@Path("put")
	@Produces(MediaType.APPLICATION_JSON)
	public int put(String cart) {
		
		return OrderCtrl.put(cart);
	}
	
	
}
