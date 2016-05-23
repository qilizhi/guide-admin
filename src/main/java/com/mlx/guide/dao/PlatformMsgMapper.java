package com.mlx.guide.dao;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.entity.PlatformMsg;
import com.mlx.guide.entity.UserInfo;

public interface PlatformMsgMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformMsg record);

    int insertSelective(PlatformMsg record);

    PlatformMsg selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformMsg record);

    int updateByPrimaryKey(PlatformMsg record);
    
    List<PlatformMsg> getPlatformMsgList();

    
	PageList<PlatformMsg> getPageList(PlatformMsg platformMsg,
			PageBounds pageBounds);

	 PlatformMsg getPlatformMsgById(Long  id);
}