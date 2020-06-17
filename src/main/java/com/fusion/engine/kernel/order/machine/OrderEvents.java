package com.fusion.engine.kernel.order.machine;

public enum OrderEvents {
    e_submit,           // 提交事件
    e_audit,            // 审核事件
    e_confirm,          // 确认保证金事件
    e_upload,           // 上传合同事件
    e_send,             // 发送银行事件
    e_sign,             // 签订担保事件
    e_offer,            // 放款事件
}
