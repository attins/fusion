package com.fusion.engine.kernel.order.machine;

public enum OrderStates {
	s_save,						// 申请保存
	s_submit,					// 申请提交
	s_audit,					// 申请审核
	s_confirmDeposit,			// 确认保证金
	s_uploadContracts,			// 上传合同
	s_sendBank,					// 申请发送银行
	s_signGuarantee,			// 签订担保
	s_offerLoans,				// 银行放款
}