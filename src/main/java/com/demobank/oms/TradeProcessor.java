package com.demobank.oms;

import com.demobank.oms.model.Order;
import com.demobank.oms.model.Trade;

public interface TradeProcessor {

    Trade processTrade(Order order);
}
