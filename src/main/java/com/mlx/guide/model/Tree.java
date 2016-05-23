package com.mlx.guide.model;

import java.util.ArrayList;
import java.util.List;
/**
 * @author QiQi-04-PC
 *
 */

public class Tree {
	
	private Integer id;//objectId
	private String text;//objectName
	private Integer parentId;
	List<Tree> children=new ArrayList<Tree>();
	private State state;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public List<Tree> getChildren() {
		return children;
	}
	public void setChildren(List<Tree> children) {
		this.children = children;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}

	
	

}
