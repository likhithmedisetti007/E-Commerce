package com.likhith.demo.orderservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.likhith.demo.orderservice.client.ProductClient;
import com.likhith.demo.orderservice.document.Order;
import com.likhith.demo.orderservice.dto.Product;
import com.likhith.demo.orderservice.repository.OrderRepository;

@Component
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	ProductClient productClient;

	@Override
	public List<Order> getOrderDetails(List<String> ids) {

		List<Order> orders = new ArrayList<>();

		try {
			orders = orderRepository.findByIdIn(ids);

			if (!CollectionUtils.isEmpty(orders)) {
				for (Order order : orders) {
					List<Product> products = productClient.getProductDetails(order.getProductIds());
					order.setProducts(products);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orders;
	}

	@Override
	public List<String> addOrderDetails(List<Order> orders) {

		List<String> orderIds = new ArrayList<>();

		try {
			for (Order order : orders) {
				Order orderFromDB = orderRepository.insert(order);

				List<String> productIds = new ArrayList<>();
				List<Product> unavailableProducts = new ArrayList<>();

				if (!CollectionUtils.isEmpty(order.getProducts())) {
					for (Product product : order.getProducts()) {
						String productId = productClient.checkProductExists(product.getName(), product.getCost(),
								product.getCategory());

						if (null != productId) {
							productIds.add(productId);
						} else {
							unavailableProducts.add(product);
						}
					}
				}

				if (!CollectionUtils.isEmpty(unavailableProducts)) {
					List<String> newProductIds = productClient.addProductDetails(unavailableProducts);
					productIds.addAll(newProductIds);
				}

				if (!CollectionUtils.isEmpty(productIds)) {
					orderFromDB.setProductIds(productIds);
					orderRepository.save(orderFromDB);
				}
				orderIds.add(orderFromDB.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderIds;
	}

	@Override
	public Boolean updateOrderDetails(Order order, String id) {

		Boolean updateSuccess;
		try {
			Optional<Order> orderOptional = orderRepository.findById(id);
			if (orderOptional.isPresent()) {
				Order orderFromDB = orderOptional.get();
				orderFromDB.setOrderStatus(order.getOrderStatus());

				List<String> productIds = new ArrayList<>();
				List<Product> unavailableProducts = new ArrayList<>();

				if (!CollectionUtils.isEmpty(order.getProducts())) {
					for (Product product : order.getProducts()) {
						String productId = productClient.checkProductExists(product.getName(), product.getCost(),
								product.getCategory());
						if (null != productId) {
							if (!CollectionUtils.isEmpty(orderFromDB.getProductIds()) && !orderFromDB.getProductIds()
									.stream().anyMatch(s -> s.equalsIgnoreCase(productId))) {
								productIds.add(productId);
							}
						} else {
							unavailableProducts.add(product);
						}
					}

					if (!CollectionUtils.isEmpty(unavailableProducts)) {
						List<String> newProductIds = productClient.addProductDetails(unavailableProducts);
						productIds.addAll(newProductIds);
					}

					if (CollectionUtils.isEmpty(orderFromDB.getProductIds())) {
						orderFromDB.setProductIds(productIds);
					} else {
						orderFromDB.getProductIds().addAll(productIds);
					}
				}

				orderRepository.save(orderFromDB);

				updateSuccess = true;
			} else {
				updateSuccess = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			updateSuccess = false;
		}

		return updateSuccess;
	}

	@Override
	public Boolean deleteOrderDetails(String id) {

		Boolean deleteSuccess;
		try {
			Optional<Order> orderOptional = orderRepository.findById(id);

			if (orderOptional.isPresent()) {

				Order orderFromDB = orderOptional.get();
				orderRepository.delete(orderFromDB);

				deleteSuccess = true;
			} else {
				deleteSuccess = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			deleteSuccess = false;
		}

		return deleteSuccess;
	}

	@Override
	public Boolean checkOrderExists(String id) {
		Boolean isExists = orderRepository.existsById(id);
		return isExists;
	}
}