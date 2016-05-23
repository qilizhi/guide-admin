package com.mlx.guide.dao;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.entity.WxPublic;


public interface WxPublicMapper {

	 int deleteByPrimaryKey(Long id);

	    int insert(WxPublic wxPublic);

	    int insertSelective(WxPublic wxPublic);

	    WxPublic selectByPrimaryKey(Long id);

	    int updateByPrimaryKeySelective(WxPublic wxPublic);

	    int updateByPrimaryKey(WxPublic wxPublic);

		PageList<WxPublic> getWxPublicPageList(WxPublic wxPublic,
				PageBounds pageBounds);

		List<WxPublic> getAllwxPublic();
}