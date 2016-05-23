package com.mlx.guide.util;

import java.util.Map;

/**
 * 查询条件
 * @author quan
 *
 */
public class PageCondition {

	/**
	 * 当前页
	 */
	private Integer pageNo = 1;

	/**
	 * 每页数量
	 */
	private Integer pageSize = 10;

	/**
	 * 条件
	 * <pre>Key=对象字段名称,Value=对象字段值</pre>
	 */
	private Map<String, Object> condition;

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo( Integer pageNo ) {
		this.pageNo = pageNo == null ? 1 : pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize( Integer pageSize ) {
		this.pageSize = pageSize == null ? 10 : pageSize;
	}

	public Map<String, Object> getCondition() {
		return condition;
	}

	public void setCondition( Map<String, Object> condition ) {
		this.condition = condition;
	}

}
