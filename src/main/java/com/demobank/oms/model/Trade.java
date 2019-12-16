package com.demobank.oms.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
public class Trade {
    long orderId;
    long tradeId;
    BigDecimal tradePrice;
    long tradeQuantity;
    TradeStatus tradeStatus;
}