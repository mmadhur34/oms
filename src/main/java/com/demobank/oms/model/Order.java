package com.demobank.oms.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Order {
    long orderId;
    String securityIdentifier;
    long quantity;
    Direction direction;
    String accountNumber;
    OrderStatus currentStatus;
    OrderRequestType orderRequestType;
    BigDecimal price;
}
enum OrderRequestType {
    NEW, AMEND, CANCEL;
}
