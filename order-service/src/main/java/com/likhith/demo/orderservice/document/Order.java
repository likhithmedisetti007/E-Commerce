package com.likhith.demo.orderservice.document;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.likhith.demo.orderservice.dto.Product;

@Document(collection = "order")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "order_id", "order_status", "customer_id", "productIds", "products" })
public class Order {

	@Id
	@JsonProperty("order_id")
	private String id;
	@JsonProperty("order_status")
	private String orderStatus;
	@JsonProperty("customer_id")
	private String customerId;
	private List<String> productIds;
	@Transient
	private List<Product> products;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public List<String> getProductIds() {
		return productIds;
	}

	public void setProductIds(List<String> productIds) {
		this.productIds = productIds;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderStatus=" + orderStatus + ", customerId=" + customerId + ", productIds="
				+ productIds + ", products=" + products + "]";
	}

}