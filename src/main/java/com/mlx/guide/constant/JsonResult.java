package com.mlx.guide.constant;

import com.github.miemiedev.mybatis.paginator.domain.PageList;

public class JsonResult {
	
	private Integer code;
	
	private String msg;

	private Integer page;
	
	private Integer pageSize;
	
	private Integer pageCount;
	
	private Integer rowCount;
	
	private Integer total;
	
	private Object result;
	
	public JsonResult(){
		
	}
	
	public JsonResult(ExceptionCode exceptionCode) {
		this.code = exceptionCode.getCodeNo();
		this.msg = exceptionCode.getCodeName();
	}
	
	public JsonResult(ExceptionCode exceptionCode , Object result) {
		this.code = exceptionCode.getCodeNo();
		this.msg = exceptionCode.getCodeName();
		this.result = result;
		this.setPage();
	}
	
	public JsonResult(Integer status , String message , Object result) {
		this.code = status;
		this.msg = message;
		this.result = result;
		this.setPage();
	}
	
	private void setPage() {
		if(result != null && result instanceof PageList){
			PageList<?> pgList = (PageList<?>)result;
			this.page = pgList.getPaginator().getPage();
			this.pageSize = pgList.getPaginator().getLimit();
			this.pageCount = pgList.getPaginator().getTotalPages();
			this.rowCount = pgList.size();
			this.total = pgList.getPaginator().getTotalCount();
		}
	}

	public Integer getCode() {
		return code;
	}

	public void setCode( Integer code ) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg( String msg ) {
		this.msg = msg;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage( Integer page ) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize( Integer pageSize ) {
		this.pageSize = pageSize;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount( Integer pageCount ) {
		this.pageCount = pageCount;
	}

	public Integer getRowCount() {
		return rowCount;
	}

	public void setRowCount( Integer rowCount ) {
		this.rowCount = rowCount;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal( Integer total ) {
		this.total = total;
	}

	public Object getResult() {
		return result;
	}

	public void setResult( Object result ) {
		this.result = result;
		this.setPage();
	}
	
}