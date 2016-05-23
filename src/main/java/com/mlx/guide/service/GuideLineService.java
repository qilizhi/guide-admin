package com.mlx.guide.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.constant.EFlag;
import com.mlx.guide.dao.GuideLineMapper;
import com.mlx.guide.entity.GuideLine;

@Service
@Transactional
public class GuideLineService {

	@Autowired
	private GuideLineMapper guideLineMapper;

	public List<GuideLine> getGuideLineList() {
		return guideLineMapper.getGuideLineList();
	}

	public PageList<GuideLine> getGuideLineList(PageBounds pageBounds) {
		return guideLineMapper.getGuideLineList(pageBounds);
	}

	public List<GuideLine> getGuideLinePageList(GuideLine guideLine) {
		return guideLineMapper.getGuideLinePageList(guideLine);
	}

	public PageList<GuideLine> getGuideLinePageList(GuideLine guideLine, PageBounds pageBounds) {
		return guideLineMapper.getGuideLinePageList(guideLine, pageBounds);
	}

	public List<GuideLine> getGuideLinePageListByMap(Map<String, Object> map) {
		return guideLineMapper.getGuideLinePageListByMap(map);
	}

	public PageList<GuideLine> getGuideLinePageListByMap(Map<String, Object> map, PageBounds pageBounds) {
		return guideLineMapper.getGuideLinePageListByMap(map, pageBounds);
	}

	public GuideLine getGuideLineByPrimaryKey(Integer id) {
		return guideLineMapper.getGuideLineByPrimaryKey(id);
	}

	public GuideLine getGuideLineByLineNo(String lineNo) {
		return guideLineMapper.getGuideLineByLineNo(lineNo);
	}

	public void createGuideLine(GuideLine guideLine) {
		guideLineMapper.createGuideLine(guideLine);
	}

	public void createGuideLineSelective(GuideLine guideLine) {
		guideLineMapper.createGuideLineSelective(guideLine);
	}

	public void createGuideLineBitch(List<GuideLine> guideLineList) {
		for (GuideLine guideLine : guideLineList) {
			guideLineMapper.createGuideLine(guideLine);
		}
	}

	public void updateGuideLine(GuideLine guideLine) {
		guideLineMapper.updateGuideLine(guideLine);
	}

	public void updateGuideLineBitch(List<GuideLine> guideLineList) {
		for (GuideLine guideLine : guideLineList) {
			guideLineMapper.updateGuideLine(guideLine);
		}
	}

	public int updateGuideLineSelective(GuideLine guideLine) {
		return guideLineMapper.updateGuideLineSelective(guideLine);
	}

	public void updateGuideLineSelectiveBitch(List<GuideLine> guideLineList) {
		for (GuideLine guideLine : guideLineList) {
			guideLineMapper.updateGuideLineSelective(guideLine);
		}
	}

	public int deleteGuideLine(Integer id) {
		return guideLineMapper.deleteGuideLine(id);
	}

	public void deleteGuideLineBitch(List<Integer> idList) {
		for (Integer id : idList) {
			GuideLine gl=new GuideLine();
			gl.setId(id);
			gl.setFlag(EFlag.INVALID.getId());
			guideLineMapper.updateGuideLineSelective(gl);
		}
	}

	public PageList<GuideLine> getGuideLineByUserNoPageList(String userNo, GuideLine guideLine, PageBounds pageBounds) {

		return guideLineMapper.getGuideLineByUserNoPageList(userNo, guideLine, pageBounds);
	}

	public PageList<GuideLine> searcheGuideLinePageList(GuideLine guideLine, PageBounds pageBounds) {

		return guideLineMapper.searcheGuideLinePageList(guideLine, pageBounds);
	}

}