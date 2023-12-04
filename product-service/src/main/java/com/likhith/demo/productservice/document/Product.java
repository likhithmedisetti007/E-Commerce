package com.likhith.demo.productservice.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "product")
public class Product {

	@Id
	@JsonProperty("product_id")
	private String _id;
	@JsonProperty("product_name")
	private String name;
	@JsonProperty("product_cost")
	private String cost;
	@JsonProperty("product_qty")
	private String quantity;

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

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Product [_id=" + _id + ", name=" + name + ", cost=" + cost + ", quantity=" + quantity + "]";
	}

}