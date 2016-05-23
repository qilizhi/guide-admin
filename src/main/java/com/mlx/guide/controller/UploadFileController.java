package com.mlx.guide.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mlx.guide.UEeditor.ActionMap;
import com.mlx.guide.UEeditor.UEeditorConfigManager;
import com.mlx.guide.UEeditor.define.AppInfo;
import com.mlx.guide.UEeditor.define.BaseState;
import com.mlx.guide.UEeditor.define.State;
import com.mlx.guide.UEeditor.upload.OSSUploader;
import com.mlx.guide.constant.ExceptionCode;
import com.mlx.guide.constant.JsonResult;
import com.mlx.guide.model.FileMeta;
import com.mlx.guide.util.OSSClientUtil;

/**
 * 上传控制器
 * 
 * <pre>
 * 在实际生产环境中必须是已经登录的用户才可以使用上传功能
 * 此上传链接需要加上限制
 * </pre>
 * 
 * @author quan
 * 
 */
@Controller
@RequestMapping( "/upload" )
public class UploadFileController {

	private static Logger logger = LoggerFactory.getLogger( UploadFileController.class );
	

	
	/**
	 * ueeditor 配置
	 */
	
	@RequestMapping(value="/UEeditor",produces ={MediaType.ALL_VALUE})
	@ResponseBody
	public String actionEnter(HttpServletRequest request,String rootPath,String originalPath) {
		String result=OSSUploader.fileUpload(request,rootPath,originalPath);
		return result;
	}
	
	
	
	/**
	 * 上传
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping
	@ResponseBody
	public JsonResult upload( MultipartHttpServletRequest request ) {

		LinkedList<FileMeta> files = new LinkedList<FileMeta>();
		FileMeta fileMeta = null;
		// 创建一个迭代器
		Iterator<String> itr = request.getFileNames();
		// 循环获取文件
		while( itr.hasNext() ) {
			String paramFileName = itr.next();
			List<MultipartFile> lsFiles = request.getFiles( paramFileName );
			if( lsFiles == null || lsFiles.isEmpty() ) {
				return new JsonResult( ExceptionCode.FAIL, "没有需要上传的图片或者文件" );
			}
			for( MultipartFile mpf : lsFiles ) {
				if( !OSSClientUtil.checkFileType( mpf.getOriginalFilename() ) ) {
					logger.info( "检查到不支持的文件类型" );
					continue;
				}
				fileMeta = new FileMeta();
				fileMeta.setFileName( mpf.getOriginalFilename() );
				fileMeta.setFileSize( mpf.getSize() / 1024 + " Kb" );
				fileMeta.setFileType( mpf.getContentType() );
				try {
					fileMeta.setBytes( mpf.getBytes() );
					String fileUrl = OSSClientUtil.uploadFile( mpf );
					fileMeta.setFilePath( fileUrl );
				}
				catch( IOException e ) {
					logger.error( e.getMessage(), e );
				}
				files.add( fileMeta );
			}
		}
		return new JsonResult( ExceptionCode.SUCCESSFUL, files );
	}

	/**
	 * 上传图片
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping( value = "/imgData", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	public JsonResult uploadImgData( HttpServletRequest request, HttpServletResponse response ) {
		String path = null;
		String fileData = request.getParameter( "fileData" );
		String[] fileDatas = request.getParameterValues( "fileData[]" );
		List<String> paths = new ArrayList<String>();
		try {
			if( fileData != null && !fileData.isEmpty() ) {
				path = OSSClientUtil.upload( fileData );
				return new JsonResult( ExceptionCode.SUCCESSFUL, path );
			}
			if( fileDatas != null && fileDatas.length > 0 ) {
				for( int i = 0; i < fileDatas.length; i++ ) {
					path = OSSClientUtil.upload( fileDatas[i] );
					paths.add( path );
				}
				return new JsonResult( ExceptionCode.SUCCESSFUL, paths );
			}
		}
		catch( IOException e ) {
			logger.error( e.getMessage(), e );
		}
		return new JsonResult( ExceptionCode.FAIL, "没有需要上传的图片或者文件" );
	}
	
	
	

}
