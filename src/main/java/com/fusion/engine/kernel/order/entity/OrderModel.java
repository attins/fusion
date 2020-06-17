package com.fusion.engine.kernel.order.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderModel {
    String orderNo;
    String company;
    String bank;
    boolean flag;
    String msg;
    List list;
}
