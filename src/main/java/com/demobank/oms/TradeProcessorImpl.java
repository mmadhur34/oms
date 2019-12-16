package com.demobank.oms;

import com.demobank.oms.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class TradeProcessorImpl implements TradeProcessor {
    AtomicLong tradeIdGen = new AtomicLong(1);
    @Autowired
    private OrderListener orderListener;
    @Override
    public Trade processTrade(Order order) {
        Trade trade;
        TradeStatus tradeStatus=TradeStatus.ACCEPTED;
        //Dummy order filling logic, qty<100 autofills.
        //qty>100 reject order
        if (order.getQuantity()>100){
            trade=new Trade(order.getOrderId(),tradeIdGen.getAndAdd(1),order.getPrice(),order.getQuantity(),
                    TradeStatus.REJECT);
            orderListener.updateReceived(new OrderResponse(order.getOrderId(), OrderStatus.newRejected,
                    trade.getTradeStatus(),order));
        }else{
            //Autofill
            trade=new Trade(order.getOrderId(),tradeIdGen.getAndAdd(1),order.getPrice(),order.getQuantity(),
                    TradeStatus.FILL);
            orderListener.updateReceived(new OrderResponse(order.getOrderId(),OrderStatus.newAccepted,
                    trade.getTradeStatus(),order));
        }
        return trade;
    }
}
