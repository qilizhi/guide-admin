package com.mlx.guide.service;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.dao.GuideInfoMapper;
import com.mlx.guide.entity.GuideInfo;
import com.mlx.guide.model.GuideInfoModel;

@Service
@Transactional
public class GuideInfoService {

	@Autowired
	private GuideInfoMapper guideInfoMapper;
	
    public int deleteByPrimaryKey(Long id){
    	return guideInfoMapper.deleteByPrimaryKey(id);
    }

    public int insert(GuideInfo record){
    	return guideInfoMapper.insert(record);
    }

    public int insertSelective(GuideInfo record){
    	return guideInfoMapper.insertSelective(record);
    }

    public GuideInfo selectByPrimaryKey(Long id){
    	return guideInfoMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(GuideInfo record){
    	return guideInfoMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(GuideInfo record){
    	return guideInfoMapper.updateByPrimaryKey(record);
    }
    
    public GuideInfo getGuideInfoByUserNo(String userNo){
    	return guideInfoMapper.getGuideInfoByUserNo(userNo);
    }

	public PageList<GuideInfoModel> getGuideInfoPageList(
			GuideInfoModel guideInfoModel, PageBounds pageBounds) {
	
		return guideInfoMapper.getGuideInfoPageList(guideInfoModel,pageBounds);
	}
	public List<GuideInfo> getGuideInfoList(GuideInfo guideInfo) {
		return guideInfoMapper.getGuideInfoList(guideInfo);
	}
	public PageList<GuideInfo> getGuideInfoList(GuideInfo guideInfo,PageBounds pb) {
		return guideInfoMapper.getGuideInfoList(guideInfo,pb);
	}

	/**
	 * 查询本月的新增用户数
	 * @param date 当月的任何时间
	 * @return 
	 */
	public Long countByDate(Integer month) {
		// TODO Auto-generated method stub
		return guideInfoMapper.countByDate(month);
	}

	/**
	 * 分页没通过审核状态的导游
	 * @param guideInfoModel
	 * @param pageBounds
	 * @return
	 */
	public PageList<GuideInfoModel> getPageListByAuthstr(
			GuideInfoModel guideInfoModel, PageBounds pageBounds) {
	 
		return guideInfoMapper.getPageListByAuthstr( guideInfoModel,pageBounds);
	}
}

