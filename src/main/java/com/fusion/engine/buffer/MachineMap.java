package com.fusion.engine.buffer;

import com.fusion.engine.kernel.order.machine.OrderEvents;
import com.fusion.engine.kernel.order.machine.OrderStates;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MachineMap implements StateMachinePersist<OrderStates, OrderEvents, String> {

    private Map<String, StateMachineContext<OrderStates, OrderEvents>> map = new HashMap<String, StateMachineContext<OrderStates, OrderEvents>>();

    @Override
    public void write(StateMachineContext<OrderStates, OrderEvents> context, String contextObj) throws Exception {
        map.put(contextObj, context);
    }

    @Override
    public StateMachineContext<OrderStates, OrderEvents> read(String contextObj) throws Exception {
        return map.get(contextObj);
    }
}
