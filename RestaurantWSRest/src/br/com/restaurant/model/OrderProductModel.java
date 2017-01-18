package br.com.restaurant.model;

@SuppressWarnings("serial")
public class OrderProductModel extends AbstractModel {
	
	private int order_id;
	private ProductModel product;
	private int quantity;
	private String total_price;
	private String total_final_price;
	
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public ProductModel getProduct() {
		return product;
	}
	public void setProduct(ProductModel product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getTotal_price() {
		return total_price;
	}
	public void setTotal_price(String total_price) {
		this.total_price = total_price;
	}
	public String getTotal_final_price() {
		return total_final_price;
	}
	public void setTotal_final_price(String total_final_price) {
		this.total_final_price = total_final_price;
	}
	
		
	
}
