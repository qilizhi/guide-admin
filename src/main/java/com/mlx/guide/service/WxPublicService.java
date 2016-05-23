package com.mlx.guide.service;



import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.dao.WxPublicMapper;
import com.mlx.guide.entity.WxPublic;



@Service
@Transactional
public class WxPublicService {

	@Autowired
	private WxPublicMapper wxPublicMapper;

	
	public PageList<WxPublic> getWxPublicPageList(WxPublic wxPublic,
			PageBounds pageBounds) {
		return wxPublicMapper.getWxPublicPageList(wxPublic,pageBounds);
	}

	public WxPublic getWxPublicById(Long id) {
		return wxPublicMapper.selectByPrimaryKey(id);
	}
    
	/**
	 * 修改
	 * @param wxPublic
	 */
	@Transactional(readOnly=false)
	public void updateWxPublic(WxPublic wxPublic) {
		wxPublic.setUpdateTime(new Date());
		wxPublicMapper.updateByPrimaryKey(wxPublic);
		
	}
   
	/**
	 * 删除
	 * @param id
	 */
	public void deleteWxPublicById(Long id) {
		wxPublicMapper.deleteByPrimaryKey(id);
		
	}

	public void insertWxpublic(WxPublic wxPublic) {
		wxPublic.setFlag("1");
		wxPublicMapper.insertSelective(wxPublic);
		
	}
   
	public List<WxPublic> getAllwxPublic() {	
		return wxPublicMapper.getAllwxPublic();
	}

	
}