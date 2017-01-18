package br.com.restaurant.model;

import java.util.List;

@SuppressWarnings("serial")
public class OrderModel extends AbstractModel {
	
	private ClientModel client;
	private StatusModel status;
	private String datetime;
	private SiteModel site;
	private List<OrderProductModel> products;
	
	
	public ClientModel getClient() {
		return client;
	}
	public void setClient(ClientModel client) {
		this.client = client;
	}
	public StatusModel getStatus() {
		return status;
	}
	public void setStatus(StatusModel status) {
		this.status = status;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public SiteModel getSite() {
		return site;
	}
	public void setSite(SiteModel site) {
		this.site = site;
	}
	public List<OrderProductModel> getProducts() {
		return products;
	}
	public void setProducts(List<OrderProductModel> products) {
		this.products = products;
	}
	
	
	
	
	
	

	
	
	
}
