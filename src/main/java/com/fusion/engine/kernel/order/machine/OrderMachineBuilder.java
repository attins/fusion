package com.fusion.engine.kernel.order.machine;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.guard.Guard;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.EnumSet;

@Component
public class OrderMachineBuilder {

    private final static String MACHINED = "orderMachine";
    @Resource(name = "orderSubmitGuard")
    private Guard<OrderStates, OrderEvents> orderSubmitGuard;
    @Resource(name = "orderSignGuaranteeAction")
    private Action<OrderStates, OrderEvents> orderSignGuaranteeAction;
    @Resource(name = "ABankFlow")
    private Guard<OrderStates, OrderEvents> aBankFlow;

    public StateMachine<OrderStates, OrderEvents> build(BeanFactory beanFactory) throws Exception {
        StateMachineBuilder.Builder<OrderStates, OrderEvents> builder = StateMachineBuilder.builder();

        builder.configureConfiguration()
                .withConfiguration()
                .machineId(MACHINED)
                .beanFactory(beanFactory);

        // 申请保存
        builder.configureStates()
                .withStates()
                .initial(OrderStates.s_save)
                .states(EnumSet.allOf(OrderStates.class));

        builder.configureTransitions()
                // 申请提交
                .withExternal()
                .source(OrderStates.s_save).target(OrderStates.s_submit)
                .event(OrderEvents.e_submit)
                .guard(orderSubmitGuard)
                // 申请审核
                .and()
                .withExternal()
                .source(OrderStates.s_submit).target(OrderStates.s_audit)
                .event(OrderEvents.e_audit)
                // 确认保证金
                .and()
                .withExternal()
                .source(OrderStates.s_audit).target(OrderStates.s_confirmDeposit)
                .event(OrderEvents.e_confirm)
                // 上传合同
                .and()
                .withExternal()
                .source(OrderStates.s_confirmDeposit).target(OrderStates.s_uploadContracts)
                .event(OrderEvents.e_upload)
                // 申请发送银行
                .and()
                .withExternal()
                .source(OrderStates.s_uploadContracts).target(OrderStates.s_sendBank)
                .event(OrderEvents.e_send)
                // 银行放款
                .and()
                .withExternal()
                .source(OrderStates.s_sendBank).target(OrderStates.s_offerLoans)
                .event(OrderEvents.e_offer)
                // 签订担保
                .and()
                .withExternal()
                .source(OrderStates.s_sendBank).target(OrderStates.s_signGuarantee)
                .event(OrderEvents.e_sign)
                .guard(aBankFlow)
                .action(orderSignGuaranteeAction)
                // 银行放款
                .and()
                .withExternal()
                .source(OrderStates.s_signGuarantee).target(OrderStates.s_offerLoans)
                .event(OrderEvents.e_offer)
                .guard(aBankFlow);

        return builder.build();
    }

}
