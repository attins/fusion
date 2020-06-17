package com.fusion.engine.kernel.order;


import com.fusion.engine.splitter.bank.BBank.Handle;
import com.fusion.engine.splitter.bank.BBank.Model;
import com.fusion.engine.buffer.MessageMap;
import com.fusion.engine.kernel.order.entity.OrderModel;
import com.fusion.engine.kernel.order.machine.OrderEvents;
import com.fusion.engine.kernel.order.machine.OrderMachineBuilder;
import com.fusion.engine.kernel.order.machine.OrderStates;
import org.springframework.beans.factory.support.StaticListableBeanFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderController {

    @Resource
    private OrderMachineBuilder orderStateOrderMachineBuilder;
    @Resource(name = "stateMachinePersist")
    private StateMachinePersister<OrderStates, OrderEvents, String> orderMemorypersister;

    @PostMapping("/submit")
    public String submit(@RequestBody OrderModel entity) throws Exception {

        Model model = Handle.convert(entity);

        StateMachine<OrderStates, OrderEvents> stateMachine = orderStateOrderMachineBuilder.build(new StaticListableBeanFactory());
        stateMachine.start();

        Message<OrderEvents> message = MessageBuilder
                .withPayload(OrderEvents.e_submit)
                .setHeader("KEY", entity)
                .build();

        stateMachine.sendEvent(message);

        orderMemorypersister.persist(stateMachine, entity.getOrderNo());

        return MessageMap.orderMessageMap.get(entity.getOrderNo());
    }

    @PostMapping("/audit")
    public String audit(@RequestBody OrderModel entity) throws Exception {

        StateMachine<OrderStates, OrderEvents> stateMachine = orderStateOrderMachineBuilder.build(new StaticListableBeanFactory());
        orderMemorypersister.restore(stateMachine, entity.getOrderNo());

        Message<OrderEvents> message = MessageBuilder
                .withPayload(OrderEvents.e_audit)
                .setHeader("KEY", entity)
                .build();
        stateMachine.sendEvent(message);

        orderMemorypersister.persist(stateMachine, entity.getOrderNo());

        return MessageMap.orderMessageMap.get(entity.getOrderNo());
    }

    @PostMapping("/confirm")
    public String confirm(@RequestBody OrderModel entity) throws Exception {

        StateMachine<OrderStates, OrderEvents> stateMachine = orderStateOrderMachineBuilder.build(new StaticListableBeanFactory());
        orderMemorypersister.restore(stateMachine, entity.getOrderNo());

        Message<OrderEvents> message = MessageBuilder
                .withPayload(OrderEvents.e_confirm)
                .setHeader("KEY", entity)
                .build();
        stateMachine.sendEvent(message);

        orderMemorypersister.persist(stateMachine, entity.getOrderNo());

        return MessageMap.orderMessageMap.get(entity.getOrderNo());
    }

    @PostMapping("/upload")
    public String upload(@RequestBody OrderModel entity) throws Exception {

        StateMachine<OrderStates, OrderEvents> stateMachine = orderStateOrderMachineBuilder.build(new StaticListableBeanFactory());
        orderMemorypersister.restore(stateMachine, entity.getOrderNo());

        Message<OrderEvents> message = MessageBuilder
                .withPayload(OrderEvents.e_upload)
                .setHeader("KEY", entity)
                .build();
        stateMachine.sendEvent(message);

        orderMemorypersister.persist(stateMachine, entity.getOrderNo());

        return MessageMap.orderMessageMap.get(entity.getOrderNo());
    }

    @PostMapping("/send")
    public String send(@RequestBody OrderModel entity) throws Exception {

        StateMachine<OrderStates, OrderEvents> stateMachine = orderStateOrderMachineBuilder.build(new StaticListableBeanFactory());
        orderMemorypersister.restore(stateMachine, entity.getOrderNo());

        Message<OrderEvents> message = MessageBuilder
                .withPayload(OrderEvents.e_send)
                .setHeader("KEY", entity)
                .build();
        stateMachine.sendEvent(message);

        orderMemorypersister.persist(stateMachine, entity.getOrderNo());

        return MessageMap.orderMessageMap.get(entity.getOrderNo());
    }

    @PostMapping("/sign")
    public String sign(@RequestBody OrderModel entity) throws Exception {

        StateMachine<OrderStates, OrderEvents> stateMachine = orderStateOrderMachineBuilder.build(new StaticListableBeanFactory());
        orderMemorypersister.restore(stateMachine, entity.getOrderNo());

        Message<OrderEvents> message = MessageBuilder
                .withPayload(OrderEvents.e_sign)
                .setHeader("KEY", entity)
                .build();
        stateMachine.sendEvent(message);

        orderMemorypersister.persist(stateMachine, entity.getOrderNo());

        return MessageMap.orderMessageMap.get(entity.getOrderNo());
    }

    @PostMapping("/offer")
    public String offer(@RequestBody OrderModel entity) throws Exception {

        StateMachine<OrderStates, OrderEvents> stateMachine = orderStateOrderMachineBuilder.build(new StaticListableBeanFactory());
        orderMemorypersister.restore(stateMachine, entity.getOrderNo());

        Message<OrderEvents> message = MessageBuilder
                .withPayload(OrderEvents.e_offer)
                .setHeader("KEY", entity)
                .build();
        stateMachine.sendEvent(message);

        orderMemorypersister.persist(stateMachine, entity.getOrderNo());

        return MessageMap.orderMessageMap.get(entity.getOrderNo());
    }

}
