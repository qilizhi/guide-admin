package com.mlx.guide.util.sensitiveword;

/**
 * 过滤结果类
 * 
 * @author quan
 * 
 */
public class FilteredResult {
	
	/**
	 * 优先级
	 */
	private Integer level;

	/**
	 * 过滤后的内容
	 */
	private String filteredContent;

	/**
	 * 敏感词
	 */
	private String badWords;

	/**
	 * 原始内容
	 */
	private String originalContent;

	public String getBadWords() {
		return this.badWords;
	}

	public void setBadWords( String badWords ) {
		this.badWords = badWords;
	}

	public FilteredResult() {
	}

	public FilteredResult(String originalContent, String filteredContent, Integer level, String badWords) {
		this.originalContent = originalContent;
		this.filteredContent = filteredContent;
		this.level = level;
		this.badWords = badWords;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel( Integer level ) {
		this.level = level;
	}

	public String getFilteredContent() {
		return this.filteredContent;
	}

	public void setFilteredContent( String filteredContent ) {
		this.filteredContent = filteredContent;
	}

	public String getOriginalContent() {
		return this.originalContent;
	}

	public void setOriginalContent( String originalContent ) {
		this.originalContent = originalContent;
	}
}
