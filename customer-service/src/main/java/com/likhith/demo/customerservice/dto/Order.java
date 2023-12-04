package com.likhith.demo.customerservice.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "order_id", "order_number", "order_status", "customer_id", "productIds", "products" })
public class Order {

	@JsonProperty("order_id")
	private String _id;
	@JsonProperty("order_number")
	private String orderNumber;
	@JsonProperty("order_status")
	private String orderStatus;
	@JsonProperty("customer_id")
	private String customerId;
	private List<String> productIds;
	private List<Product> products;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
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
		return "Order [_id=" + _id + ", orderNumber=" + orderNumber + ", orderStatus=" + orderStatus + ", customerId="
				+ customerId + ", productIds=" + productIds + ", products=" + products + "]";
	}

}