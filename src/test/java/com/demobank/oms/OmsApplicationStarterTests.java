package com.demobank.oms;

import com.demobank.oms.model.Direction;
import com.demobank.oms.model.Order;
import com.demobank.oms.model.OrderResponse;
import com.demobank.oms.model.OrderStatus;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.GsonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@SpringBootTest
@Slf4j
class OmsApplicationStarterTests {



	@Test
	void contextLoads() {
		RestTemplate restTemplate = new RestTemplate();
		String sendOrderURL = "http://localhost:8080/sendOrder";
		String getOrderURL = "http://localhost:8080/getOrderStatus";
		Order order = new Order();
		order.setOrderId(1);
		order.setAccountNumber("12345");
		order.setCurrentStatus(OrderStatus.pendingNew);
		order.setDirection(Direction.BUY);
		order.setQuantity(100);
		order.setPrice(new BigDecimal(99.00));
		order.setSecurityIdentifier("Apple");
		restTemplate.put(sendOrderURL,order,order);
		log.debug("Order sent");
		OrderResponse orderResponse=null;
	}

	@Test
	public void generateJson(){
		Order order = new Order();
		order.setOrderId(1);
		order.setAccountNumber("12345");
		order.setCurrentStatus(OrderStatus.pendingNew);
		order.setDirection(Direction.BUY);
		order.setQuantity(100);
		order.setPrice(new BigDecimal(99.00));
		order.setSecurityIdentifier("Apple");

		GsonBuilder gsonBuilder= new GsonBuilder();
		Gson gson=gsonBuilder.create();
		System.out.println(gson.toJson(order));
	}

}
