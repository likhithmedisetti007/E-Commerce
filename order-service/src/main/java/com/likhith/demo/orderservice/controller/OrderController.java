package com.likhith.demo.orderservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.likhith.demo.orderservice.document.Order;
import com.likhith.demo.orderservice.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderService orderService;

	@GetMapping("/getOrderDetails/{ids}")
	public ResponseEntity<?> getOrderDetails(@PathVariable("ids") List<String> ids) {
		List<Order> ordersList = orderService.getOrderDetails(ids);
		if (CollectionUtils.isEmpty(ordersList)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(ordersList);

	}

	@PostMapping("/addOrderDetails")
	public ResponseEntity<?> addOrderDetails(@RequestBody List<Order> orders) {
		List<String> orderIds = orderService.addOrderDetails(orders);
		if (!CollectionUtils.isEmpty(orderIds)) {
			return ResponseEntity.ok().body(orderIds);
		}
		return ResponseEntity.internalServerError().body("Order Details Not Added");
	}

	@PutMapping("/updateOrderDetails/{id}")
	public ResponseEntity<?> updateOrderDetails(@RequestBody Order order, @PathVariable("id") String id) {
		Boolean updateSuccess = orderService.updateOrderDetails(order, id);
		if (updateSuccess) {
			return ResponseEntity.ok().body(true);
		}
		return ResponseEntity.internalServerError().body(false);
	}

	@DeleteMapping("/deleteOrderDetails/{id}")
	public ResponseEntity<?> deleteOrderDetails(@PathVariable("id") String id) {
		Boolean deleteSuccess = orderService.deleteOrderDetails(id);
		if (deleteSuccess) {
			return ResponseEntity.ok().body("Order Details Deleted");
		}
		return ResponseEntity.internalServerError().body("Order Details Not Deleted");
	}

	@GetMapping("/checkOrderExists/{id}")
	public ResponseEntity<?> checkOrderExists(@PathVariable("id") String id) {
		Boolean isExists = orderService.checkOrderExists(id);
		if (isExists) {
			return ResponseEntity.ok().body(true);
		}
		return ResponseEntity.ok().body(false);
	}

}