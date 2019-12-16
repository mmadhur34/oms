package com.demobank.oms.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderResponse {
    long orderId;
    OrderStatus orderStatus;
    TradeStatus tradeStatus;
    Order order;
}
