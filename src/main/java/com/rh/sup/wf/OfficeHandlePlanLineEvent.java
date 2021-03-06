package com.rh.sup.wf;

import com.rh.core.base.Bean;
import com.rh.core.base.Context;
import com.rh.core.serv.ParamBean;
import com.rh.core.wfe.WfAct;
import com.rh.core.wfe.WfProcess;
import com.rh.core.wfe.util.AbstractLineEvent;
import com.rh.sup.serv.SupApproPlanServ;

/**署发流程
 * 办理计划 送交督察处审批
 * N4——>N22
 * @author kfzx-liuyx02
 */
public class OfficeHandlePlanLineEvent extends AbstractLineEvent {

	// 司局长的角色编码
	private final String ROLE_LEAD = "SUP006";
	// //插入代办的的当前节点
	// private final String OPERATION = "送督察处审批办理情况";
	// 办理计划状态:办理中 1
	private final String PLAN_ING_STATE = "1";
	// 办理计划状态:待审核 2
	private final String PLAN_CHECK_STATE = "2";
	
	@Override
	public void forward(WfAct wfAct, WfAct wfAct1, Bean bean) {
		//修改办理状态
		WfProcess process = wfAct.getProcess();
		Bean servBean = process.getServInstBean();
		String approId = servBean.getId();
		String deptCode = Context.getUserBean().getDeptCode();
		//将待审核修改为办理中状态
		SupApproPlanServ officePlan = new SupApproPlanServ();
		ParamBean paramBean = new ParamBean();
		paramBean.set("approId", approId);
		paramBean.set("curState", PLAN_ING_STATE);
		paramBean.set("upState", PLAN_CHECK_STATE);
		paramBean.set("deptCode", deptCode);
		officePlan.updatePlanWfState(paramBean);
	}
	
	@Override
	public void backward(WfAct wfAct, WfAct wfAct1, Bean bean) {
		//取得流程实例对象
		WfProcess process = wfAct.getProcess();
		Bean nodeBean = wfAct.getNodeInstBean();
		Bean servBean = process.getServInstBean();
		//获取当前服务的ID(主键)
		String approId = servBean.getId();
		String title = servBean.getStr("TITLE");
		//查询当前的部门code
		String deptCode = Context.getUserBean().getDeptCode();
		
		//修改当前办理状态(将办理中修改为待审核状态)
		SupApproPlanServ officePlan = new SupApproPlanServ();
		ParamBean paramBean = new ParamBean();
		paramBean.set("approId", approId);
		paramBean.set("curState", PLAN_ING_STATE);
		paramBean.set("upState", PLAN_CHECK_STATE);
		paramBean.set("deptCode", deptCode);
		officePlan.updatePlanWfState(paramBean);
		
	}
	

}
