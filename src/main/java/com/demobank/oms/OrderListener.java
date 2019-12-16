package com.demobank.oms;

import com.demobank.oms.model.OrderResponse;

public interface OrderListener {
    void updateReceived(OrderResponse orderResponse);
}
