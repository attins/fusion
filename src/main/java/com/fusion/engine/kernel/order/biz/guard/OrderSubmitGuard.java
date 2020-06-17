package com.fusion.engine.kernel.order.biz.guard;


import com.fusion.engine.buffer.MessageMap;
import com.fusion.engine.kernel.order.entity.OrderModel;
import com.fusion.engine.kernel.order.machine.OrderEvents;
import com.fusion.engine.kernel.order.machine.OrderStates;
import org.springframework.statemachine.StateContext;
import org.springframework.stereotype.Component;

@Component
public class OrderSubmitGuard implements org.springframework.statemachine.guard.Guard<OrderStates, OrderEvents> {

    @Override
    public boolean evaluate(StateContext<OrderStates, OrderEvents> context) {
        OrderModel entity = (OrderModel) context.getMessage().getHeaders().get("KEY");

//        核心系统业务逻辑校验

        MessageMap.orderMessageMap.put(entity.getOrderNo(), entity.getMsg());
        return entity.isFlag();
    }
}
