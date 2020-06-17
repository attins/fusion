package com.fusion.engine.kernel.order.biz.action;

import com.fusion.engine.kernel.order.entity.OrderModel;
import com.fusion.engine.kernel.order.machine.OrderEvents;
import com.fusion.engine.kernel.order.machine.OrderStates;
import org.springframework.statemachine.StateContext;
import org.springframework.stereotype.Component;

@Component
public class OrderSignGuaranteeAction implements org.springframework.statemachine.action.Action<OrderStates, OrderEvents> {

    @Override
    public void execute(StateContext<OrderStates, OrderEvents> context) {

        OrderModel entity = (OrderModel) context.getMessage().getHeaders().get("KEY");

//        核心系统业务逻辑判断

//        持久化

    }
}
