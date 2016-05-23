package com.mlx.guide.dao;

import java.util.List;
import java.util.Map;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.entity.AdvInfo;


public interface AdvInfoMapper {

    public List<AdvInfo> getAdvInfoList();

	public PageList<AdvInfo> getAdvInfoList( PageBounds pageBounds );
	
	public List<AdvInfo> getAdvInfoPageList( AdvInfo advInfo );
	
	public PageList<AdvInfo> getAdvInfoPageList(AdvInfo advInfo, PageBounds pageBounds );
	
	public List<AdvInfo> getAdvInfoPageListByMap( Map<String, Object> map );
	
	public PageList<AdvInfo> getAdvInfoPageListByMap(Map<String, Object> map, PageBounds pageBounds );
	
	public AdvInfo getAdvInfoByPrimaryKey( Integer id );
	
	public void createAdvInfo( AdvInfo advInfo );
	
	public void createAdvInfoSelective(AdvInfo advInfo);
	
	public int updateAdvInfo(AdvInfo advInfo);
	
	public int updateAdvInfoSelective(AdvInfo advInfo);
	
	public int deleteAdvInfo(Integer id);
}