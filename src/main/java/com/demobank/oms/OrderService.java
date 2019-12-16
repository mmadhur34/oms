package com.demobank.oms;

import com.demobank.oms.model.Order;
import com.demobank.oms.model.OrderResponse;
import com.demobank.oms.model.OrderStatus;

public interface OrderService {
    OrderResponse processOrder(Order order);
    OrderResponse getOrderStatus(long orderId);
}
