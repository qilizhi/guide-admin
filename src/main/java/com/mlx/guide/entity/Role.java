package com.mlx.guide.entity;

import java.io.Serializable;

public class Role implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	

	private Integer id;

    private Integer parentId;

    private String name;

    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Override
	public String toString() {
		return "Role [id=" + id + ", parentId=" + parentId + ", name=" + name + ", description=" + description + "]";
	}
}