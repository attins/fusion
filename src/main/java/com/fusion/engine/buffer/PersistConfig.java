package com.fusion.engine.buffer;

import com.fusion.engine.kernel.order.machine.OrderEvents;
import com.fusion.engine.kernel.order.machine.OrderStates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;

import javax.annotation.Resource;

@Configuration
public class PersistConfig {

    @Resource
    private MachineMap inMemoryStateMachinePersist;

    @Bean(name = "stateMachinePersist")
    public StateMachinePersister<OrderStates, OrderEvents, String> getStateMachinePersist() {
        return new DefaultStateMachinePersister<>(inMemoryStateMachinePersist);
    }
}
