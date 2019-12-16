package com.demobank.oms;

import com.demobank.oms.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class OrderServiceImpl implements OrderService,OrderListener {
    private Map<Long, Order> orderCache=new HashMap<>();
    private Map<Long, OrderResponse> orderStatusMap=new HashMap<>();

    @Value("${maxQtyFill}")
    private long maxQtyPerOrder;

    @Value("#{'${valid.securityIds}'.split(',')}")
    private List<String> validSecurities;

    @Autowired
    private TradeProcessor tradeProcessor;

    @Override
    public OrderResponse processOrder(Order order){
      log.info("received order {}",order);
      if(!validateOrder(order)){
          return new OrderResponse(order.getOrderId(), OrderStatus.newRejected, TradeStatus.REJECT,order);
      }
      log.debug("Valid security");
      order.setCurrentStatus(OrderStatus.pendingNew);

      Trade response = tradeProcessor.processTrade(order);
      return new OrderResponse(order.getOrderId(),OrderStatus.pendingNew,response.getTradeStatus(),order);

    }

    private boolean validateOrder(Order order) {
        if(!validSecurities.contains(order.getSecurityIdentifier()) || order.getQuantity()<=0 ||
                order.getPrice().compareTo(new BigDecimal(0))==-1)
            return false;
        else
            return true;
    }

    @Override
    public OrderResponse getOrderStatus(long orderId){
        return orderStatusMap.get(orderId);
    }

    @Override
    public void updateReceived(OrderResponse orderResponse) {

        orderStatusMap.put(orderResponse.getOrderId(),orderResponse);
    }
}
