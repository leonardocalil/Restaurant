package br.com.restaurant.model;

@SuppressWarnings("serial")
public class SiteModel extends AbstractModel {
	
	private String name;
	private int physical_store;
	private String document;
	private String address_name;
	private int address_number;
	private String address_complement;
	private String zip_code;
	private String phone;
	private String email;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPhysical_store() {
		return physical_store;
	}

	public void setPhysical_store(int physical_store) {
		this.physical_store = physical_store;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getAddress_name() {
		return address_name;
	}

	public void setAddress_name(String address_name) {
		this.address_name = address_name;
	}

	public int getAddress_number() {
		return address_number;
	}

	public void setAddress_number(int address_number) {
		this.address_number = address_number;
	}

	public String getAddress_complement() {
		return address_complement;
	}

	public void setAddress_complement(String address_complement) {
		this.address_complement = address_complement;
	}

	public String getZip_code() {
		return zip_code;
	}

	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
	
}
