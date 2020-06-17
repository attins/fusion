package com.fusion.engine.splitter.flow;

import com.fusion.engine.kernel.order.entity.OrderModel;
import com.fusion.engine.kernel.order.machine.OrderEvents;
import com.fusion.engine.kernel.order.machine.OrderStates;
import org.springframework.statemachine.StateContext;
import org.springframework.stereotype.Component;

@Component
public class ABankFlow implements org.springframework.statemachine.guard.Guard<OrderStates, OrderEvents> {

    @Override
    public boolean evaluate(StateContext<OrderStates, OrderEvents> context) {
        OrderModel entity = (OrderModel) context.getMessage().getHeaders().get("KEY");
//        流程银行特例差异处理

        return "ABank".equals(entity.getBank());
    }
}
