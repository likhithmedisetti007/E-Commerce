package com.likhith.demo.productservice.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Document(collection = "product")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "product_id", "product_name", "product_cost", "product_category" })
public class Product {

	@Id
	@JsonProperty("product_id")
	private String id;
	@JsonProperty("product_name")
	private String name;
	@JsonProperty("product_cost")
	private String cost;
	@JsonProperty("product_category")
	private String category;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", cost=" + cost + ", category=" + category + "]";
	}

}