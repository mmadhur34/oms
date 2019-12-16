package com.demobank.oms;

import com.demobank.oms.model.Order;
import com.demobank.oms.model.OrderResponse;
import com.demobank.oms.model.OrderStatus;
import com.demobank.oms.model.TradeStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class OMSRequestController {

    @Autowired
    private OrderService orderService;
    @RequestMapping(method = RequestMethod.PUT, value = "/sendOrder",
            consumes = "application/json", produces = "application/json")
    public OrderResponse sendOrder(@RequestBody Order order){
        log.info("Order sent {}", order);
        if(order==null)
            return new OrderResponse(0,OrderStatus.newRejected, TradeStatus.REJECT,order);
        return orderService.processOrder(order);
    }

    @GetMapping("/getOrderStatus/{id}")
    public OrderResponse getOrderStatus(@PathVariable("id") long orderid){
        log.info("service called");
        return orderService.getOrderStatus(orderid);
    }
}
