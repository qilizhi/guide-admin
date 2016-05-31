package com.mlx.guide.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.dao.SysBizLogMapper;
import com.mlx.guide.entity.SysBizLog;
import com.mlx.guide.model.OrderRefundModel;
import com.mlx.guide.shiro.ShiroDbRealm;
import com.mlx.guide.shiro.ShiroDbRealm.ShiroUser;

@Service
@Transactional
public class SysBizLogService {

	@Autowired
	private SysBizLogMapper sysBizLogMapper;

	public List<SysBizLog> getSysBizLogList() {
		return sysBizLogMapper.getSysBizLogList();
	}

	public PageList<SysBizLog> getSysBizLogList(PageBounds pageBounds) {
		return sysBizLogMapper.getSysBizLogList(pageBounds);
	}

	public List<SysBizLog> getSysBizLogPageList(SysBizLog sysBizLog) {
		return sysBizLogMapper.getSysBizLogPageList(sysBizLog);
	}

	public PageList<SysBizLog> getSysBizLogPageList(SysBizLog sysBizLog, PageBounds pageBounds) {
		return sysBizLogMapper.getSysBizLogPageList(sysBizLog, pageBounds);
	}

	public List<SysBizLog> getSysBizLogPageListByMap(Map<String, Object> map) {
		return sysBizLogMapper.getSysBizLogPageListByMap(map);
	}

	public PageList<SysBizLog> getSysBizLogPageListByMap(Map<String, Object> map, PageBounds pageBounds) {
		return sysBizLogMapper.getSysBizLogPageListByMap(map, pageBounds);
	}

	public SysBizLog getSysBizLogByPrimaryKey(Integer id) {
		return sysBizLogMapper.getSysBizLogByPrimaryKey(id);
	}

	public void createSysBizLog(SysBizLog sysBizLog) {
		sysBizLogMapper.createSysBizLog(sysBizLog);
	}

	public void createSysBizLogSelective(SysBizLog sysBizLog) {
		sysBizLogMapper.createSysBizLogSelective(sysBizLog);
	}

	public void createSysBizLogBitch(List<SysBizLog> sysBizLogList) {
		for (SysBizLog sysBizLog : sysBizLogList) {
			sysBizLogMapper.createSysBizLog(sysBizLog);
		}
	}

	public void updateSysBizLog(SysBizLog sysBizLog) {
		sysBizLogMapper.updateSysBizLog(sysBizLog);
	}

	public void updateSysBizLogBitch(List<SysBizLog> sysBizLogList) {
		for (SysBizLog sysBizLog : sysBizLogList) {
			sysBizLogMapper.updateSysBizLog(sysBizLog);
		}
	}

	public void updateSysBizLogSelective(SysBizLog sysBizLog) {
		sysBizLogMapper.updateSysBizLogSelective(sysBizLog);
	}

	public void updateSysBizLogSelectiveBitch(List<SysBizLog> sysBizLogList) {
		for (SysBizLog sysBizLog : sysBizLogList) {
			sysBizLogMapper.updateSysBizLogSelective(sysBizLog);
		}
	}

	public void deleteSysBizLog(Integer id) {
		sysBizLogMapper.deleteSysBizLog(id);
	}

	public void deleteSysBizLogBitch(List<Integer> idList) {
		for (Integer id : idList) {
			sysBizLogMapper.deleteSysBizLog(id);
		}
	}
     
	/**
	 * 保存退款日志
	 * @param orderRefundModel
	 */
	public void saveOrderRefundLog(OrderRefundModel orderRefundModel) {
		ShiroUser shiroUser = ShiroDbRealm.getLoginUser();
		SysBizLog sysBizLog =new SysBizLog();
		
		sysBizLog.setBizType(6);
		sysBizLog.setFlag(1);
		sysBizLog.setOperatUserNo(shiroUser==null?"未登陆用户":shiroUser.getUserNo());
		sysBizLog.setOperatPerson(shiroUser==null?"未登陆用户":shiroUser.getName());
		StringBuilder log=new StringBuilder();
		log.append("订单号:"+orderRefundModel.getOrderId());
		log.append(",退款流水号:"+orderRefundModel.getRefundJnId());
		log.append(",订单金额:"+orderRefundModel.getPayFee());
		if(orderRefundModel.getRefundStatus().equals("RR")){
			log.append(",操作流程为:审核,审核金额为"+orderRefundModel.getAmount());
			
		//退款操作日志记录
		}else if(orderRefundModel.getRefundStatus().equals("RC")){
			log.append(",操作流程为:退款,退款金额为"+orderRefundModel.getAmount());
			
		}else{
			log.append(",异常操作退款金额"+orderRefundModel.getAmount());
	  }
		
		if(orderRefundModel.getCheckStatus().equals("1")){
			log.append(",操作:通过");
		}else if(orderRefundModel.getCheckStatus().equals("-1")){
			log.append(",操作:不通过");
		}else{
			log.append(",操作:异常操作");
		}
		
		sysBizLog.setContent(log.toString());
		sysBizLogMapper.createSysBizLogSelective(sysBizLog);
	}
}