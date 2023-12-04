package com.likhith.demo.customerservice.document;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.likhith.demo.customerservice.dto.Order;

@Document(collection = "customer")
@JsonPropertyOrder({ "customer_id", "customer_name", "customer_email", "customer_phone", "customer_city",
		"customer_state", "customer_country" })
public class Customer {

	@Id
	@JsonProperty("customer_id")
	private String _id;
	@JsonProperty("customer_name")
	private String name;
	@JsonProperty("customer_email")
	private String email;
	@JsonProperty("customer_phone")
	private String phone;
	@JsonProperty("customer_city")
	private String city;
	@JsonProperty("customer_state")
	private String state;
	@JsonProperty("customer_country")
	private String country;
	private List<Order> orders;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "Customer [_id=" + _id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", city=" + city
				+ ", state=" + state + ", country=" + country + ", orders=" + orders + "]";
	}

}