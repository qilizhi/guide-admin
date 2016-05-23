package com.mlx.guide.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.dao.AdvInfoMapper;
import com.mlx.guide.entity.AdvInfo;

@Service
@Transactional
public class AdvInfoService {

    @Autowired
	private AdvInfoMapper advInfoMapper;
	
	public List<AdvInfo> getAdvInfoList(){
		return advInfoMapper.getAdvInfoList();
	}
	
	public PageList<AdvInfo> getAdvInfoList(PageBounds pageBounds){
		return advInfoMapper.getAdvInfoList(pageBounds);
	}
	
	public List<AdvInfo> getAdvInfoPageList(AdvInfo advInfo){
		return advInfoMapper.getAdvInfoPageList(advInfo);
	}
	
	public PageList<AdvInfo> getAdvInfoPageList(AdvInfo advInfo, PageBounds pageBounds){
		return advInfoMapper.getAdvInfoPageList(advInfo,pageBounds);
	}
	
	public List<AdvInfo> getAdvInfoPageListByMap(Map<String, Object> map){
		return advInfoMapper.getAdvInfoPageListByMap(map);
	}
	
	public PageList<AdvInfo> getAdvInfoPageListByMap(Map<String, Object> map, PageBounds pageBounds){
		return advInfoMapper.getAdvInfoPageListByMap(map,pageBounds);
	}
	
	public AdvInfo getAdvInfoByPrimaryKey(Integer id){
		return advInfoMapper.getAdvInfoByPrimaryKey(id);
	}
	
	public void createAdvInfo(AdvInfo advInfo){
		advInfoMapper.createAdvInfo(advInfo);
	}
	
	public void createAdvInfoBitch(List<AdvInfo> advInfoList){
		for( AdvInfo advInfo : advInfoList ) {
			advInfoMapper.createAdvInfo(advInfo);
		}
	}
	
	public int updateAdvInfo(AdvInfo advInfo){
		return advInfoMapper.updateAdvInfo(advInfo);
	}
	
	public void updateAdvInfoBitch(List<AdvInfo> advInfoList){
	    for( AdvInfo advInfo : advInfoList ) {
		    advInfoMapper.updateAdvInfo(advInfo);
		}
	}
	
	public int updateAdvInfoSelective(AdvInfo advInfo){
	    return advInfoMapper.updateAdvInfoSelective(advInfo);
	}
	
	public void updateAdvInfoSelectiveBitch(List<AdvInfo> advInfoList){
	    for( AdvInfo advInfo : advInfoList ) {
		    advInfoMapper.updateAdvInfoSelective(advInfo);
		}
	}
	
	public int deleteAdvInfo(Integer id){
		return advInfoMapper.deleteAdvInfo(id);
	}
	
	public void deleteAdvInfoBitch(List<Integer> idList){
		for( Integer id : idList ) {
			advInfoMapper.deleteAdvInfo(id);
		}
	}

}