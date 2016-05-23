package com.mlx.guide.UEeditor.upload;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mlx.guide.UEeditor.ActionMap;
import com.mlx.guide.UEeditor.PathFormat;
import com.mlx.guide.UEeditor.UEeditorConfigManager;
import com.mlx.guide.UEeditor.define.AppInfo;
import com.mlx.guide.UEeditor.define.BaseState;
import com.mlx.guide.UEeditor.define.FileType;
import com.mlx.guide.UEeditor.define.State;
import com.mlx.guide.model.FileMeta;
import com.mlx.guide.util.OSSClientUtil;

public class OSSUploader {
	private static Logger logger = LoggerFactory.getLogger(com.mlx.guide.UEeditor.upload.OSSUploader.class);
	
	
	public static String  fileUpload(HttpServletRequest request,String rootPath,String originalPath){
		UEeditorConfigManager configManager=null;
		String actionType=null;
		actionType = request.getParameter("action");
		configManager = UEeditorConfigManager.getInstance(rootPath, originalPath);
	
		if (actionType == null || !ActionMap.mapping.containsKey(actionType)) {
			return new BaseState(false, AppInfo.INVALID_ACTION).toJSONString();
		}

		if (configManager == null || !configManager.valid()) {
			return new BaseState(false, AppInfo.CONFIG_ERROR).toJSONString();
		}

		State state = new BaseState();

		int actionCode = ActionMap.getType(actionType);

		Map<String, Object> conf = null;

		switch (actionCode) {
		case ActionMap.CONFIG:
			String sb= configManager.getAllConfig().toString();
			return sb;
		case ActionMap.UPLOAD_IMAGE:
		case ActionMap.UPLOAD_SCRAWL:
		case ActionMap.UPLOAD_VIDEO:
		case ActionMap.UPLOAD_FILE:
			conf =configManager.getConfig(actionCode);
			state = OSSUploader.upload(request,conf);
			break;
		case ActionMap.CATCH_IMAGE:
			conf = configManager.getConfig(actionCode);
			String[] list =request.getParameterValues((String) conf.get("fieldName"));
			//state = new ImageHunter(conf).capture(list);
			break;

		case ActionMap.LIST_IMAGE:
		case ActionMap.LIST_FILE:
			conf = configManager.getConfig(actionCode);
			//int start = this.getStartIndex();
			//state = new FileManager(conf).listFile(start);
			break;

		}
		String oResult= state.toString();
		return oResult;
	}
	
	public static State upload(HttpServletRequest request, Map<String, Object> conf) {
		String filedName = (String) conf.get("fieldName");
		State state = null;
		if ("true".equals(conf.get("isBase64"))) {
			state = base64Uploader(request.getParameter(filedName), conf);
		} else {
			state = binaryUploader(request, conf);
		}
		return state;
	}

	/**
	 * 二进制上传
	 * 
	 * @param request
	 * @param conf
	 * @return
	 */
	public static State binaryUploader(HttpServletRequest request, Map<String, Object> conf) {
		LinkedList<FileMeta> files = new LinkedList<FileMeta>();
		State state = new BaseState();
		FileMeta fileMeta = null;
		MultipartHttpServletRequest multiRequest = null;
		// 是multiPartContent类型
		multiRequest = (MultipartHttpServletRequest) request;
		// 创建一个迭代器
		Iterator<String> itr = multiRequest.getFileNames();
		// 循环获取文件
		while (itr.hasNext()) {
			String paramFileName = itr.next();
			List<MultipartFile> lsFiles = multiRequest.getFiles(paramFileName);
			if (lsFiles == null || lsFiles.isEmpty()) {
				// return new State( ExceptionCode.FAIL, "没有需要上传的图片或者文件" );
				return new BaseState(false, AppInfo.NOTFOUND_UPLOAD_DATA);
			}
			for (MultipartFile mpf : lsFiles) {

				fileMeta = new FileMeta();
				fileMeta.setFileName(mpf.getOriginalFilename());
				fileMeta.setFileSize(String.valueOf(mpf.getSize()));
				fileMeta.setFileType(mpf.getContentType());
				try {
					fileMeta.setBytes(mpf.getBytes());
					//上传到阿里云OSS
					String fileUrl = OSSClientUtil.uploadFile(mpf);
					fileMeta.setFilePath(fileUrl);
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
				files.add(fileMeta);
			}
		}
		if (files.size() > 0) {
			fileMeta = files.get(0);
			state.putInfo("size", fileMeta.getFileSize());
			state.putInfo("type", fileMeta.getFileType());
			state.putInfo("url", fileMeta.getFilePath());
		}

		return state;

	}

	/**
	 * 字符编码上传
	 * @param content
	 * @param conf
	 * @return
	 */
	public static State base64Uploader(String content, Map<String, Object> conf) {
		State state = new BaseState();
		byte[] data = decode(content);

		long maxSize = ((Long) conf.get("maxSize")).longValue();

		if (!validSize(data, maxSize)) {
			return new BaseState(false, AppInfo.MAX_SIZE);
		}
		String suffix = FileType.getSuffix("JPG");
		String savePath = PathFormat.parse((String) conf.get("savePath"), (String) conf.get("filename"));
		savePath = savePath + suffix;
		String url = null;
		try {
			//上传到阿里云OSS
			url = OSSClientUtil.upload(savePath, content);
			state.putInfo("url", url);
			state.putInfo("type", suffix);
			state.putInfo("original", "");
		} catch (IOException e) {

			e.printStackTrace();
			logger.error(e.getMessage());
			return new BaseState(false, AppInfo.FAILED_CREATE_FILE);
		}

		return state;
	}

	private static byte[] decode(String content) {
		return Base64.decodeBase64(content);
	}

	private static boolean validSize(byte[] data, long length) {
		return data.length <= length;
	}

}
