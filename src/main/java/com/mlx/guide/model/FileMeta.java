package com.mlx.guide.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 上传文件信息类
 * @author quan
 *
 */
@JsonIgnoreProperties({"bytes"})
public class FileMeta {

	private String fileName;
    private String fileSize;
    private String fileType;
    private Integer width;
    private Integer height;
    private String filePath;
 
    private byte[] bytes;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath( String filePath ) {
		this.filePath = filePath;
	}
    
}
