package com.mlx.guide.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.dao.GuideLineDatePriceMapper;
import com.mlx.guide.entity.GuideLineDatePrice;

@Service
@Transactional
public class GuideLineDatePriceService {

	@Autowired
	private GuideLineDatePriceMapper guideLineDatePriceMapper;

	public List<GuideLineDatePrice> getGuideLineDatePriceList() {
		return guideLineDatePriceMapper.getGuideLineDatePriceList();
	}

	public PageList<GuideLineDatePrice> getGuideLineDatePriceList(PageBounds pageBounds) {
		return guideLineDatePriceMapper.getGuideLineDatePriceList(pageBounds);
	}

	public List<GuideLineDatePrice> getGuideLineDatePricePageList(GuideLineDatePrice guideLineDatePrice) {
		return guideLineDatePriceMapper.getGuideLineDatePricePageList(guideLineDatePrice);
	}

	public PageList<GuideLineDatePrice> getGuideLineDatePricePageList(GuideLineDatePrice guideLineDatePrice,
			PageBounds pageBounds) {
		return guideLineDatePriceMapper.getGuideLineDatePricePageList(guideLineDatePrice, pageBounds);
	}

	public List<GuideLineDatePrice> getGuideLineDatePricePageListByMap(Map<String, Object> map) {
		return guideLineDatePriceMapper.getGuideLineDatePricePageListByMap(map);
	}

	public PageList<GuideLineDatePrice> getGuideLineDatePricePageListByMap(Map<String, Object> map,
			PageBounds pageBounds) {
		return guideLineDatePriceMapper.getGuideLineDatePricePageListByMap(map, pageBounds);
	}

	public GuideLineDatePrice getGuideLineDatePriceByPrimaryKey(Integer id) {
		return guideLineDatePriceMapper.getGuideLineDatePriceByPrimaryKey(id);
	}

	public List<GuideLineDatePrice> getGuideLineDatePriceByLineNo(String lineNo) {
		return guideLineDatePriceMapper.getGuideLineDatePriceByLineNo(lineNo);
	}

	public void createGuideLineDatePrice(GuideLineDatePrice guideLineDatePrice) {
		guideLineDatePriceMapper.createGuideLineDatePrice(guideLineDatePrice);
	}

	public void createGuideLineDatePriceSelective(GuideLineDatePrice guideLineDatePrice) {
		guideLineDatePriceMapper.createGuideLineDatePriceSelective(guideLineDatePrice);
	}

	public void createGuideLineDatePriceBitch(List<GuideLineDatePrice> guideLineDatePriceList) {
		for (GuideLineDatePrice guideLineDatePrice : guideLineDatePriceList) {
			guideLineDatePriceMapper.createGuideLineDatePrice(guideLineDatePrice);
		}
	}

	public void updateGuideLineDatePrice(GuideLineDatePrice guideLineDatePrice) {
		guideLineDatePriceMapper.updateGuideLineDatePrice(guideLineDatePrice);
	}

	public void updateGuideLineDatePriceBitch(List<GuideLineDatePrice> guideLineDatePriceList) {
		for (GuideLineDatePrice guideLineDatePrice : guideLineDatePriceList) {
			guideLineDatePriceMapper.updateGuideLineDatePrice(guideLineDatePrice);
		}
	}

	public void updateGuideLineDatePriceSelective(GuideLineDatePrice guideLineDatePrice) {
		guideLineDatePriceMapper.updateGuideLineDatePriceSelective(guideLineDatePrice);
	}

	public void updateGuideLineDatePriceSelectiveBitch(List<GuideLineDatePrice> guideLineDatePriceList) {
		for (GuideLineDatePrice guideLineDatePrice : guideLineDatePriceList) {
			guideLineDatePriceMapper.updateGuideLineDatePriceSelective(guideLineDatePrice);
		}
	}

	public void deleteGuideLineDatePrice(Integer id) {
		guideLineDatePriceMapper.deleteGuideLineDatePrice(id);
	}

	public void deleteGuideLineDatePriceBitch(List<Integer> idList) {
		for (Integer id : idList) {
			guideLineDatePriceMapper.deleteGuideLineDatePrice(id);
		}
	}

	public void deleteGuideLineDatePriceByDate(Map<String, Object> map) {
		guideLineDatePriceMapper.deleteGuideLineDatePriceByDate(map);

	}

	public void saveGuideLineDatePriceBitch(List<GuideLineDatePrice> guideLineDatePriceList) {
		for (GuideLineDatePrice guideLineDatePrice : guideLineDatePriceList) {
			if (guideLineDatePrice.getId() != null) {
				// guideLineDatePriceMapper.updateGuideLineDatePrice(guideLineDatePrice);
				guideLineDatePriceMapper.updateGuideLineDatePriceSelective(guideLineDatePrice);
				continue;
			}
			guideLineDatePriceMapper.createGuideLineDatePriceSelective(guideLineDatePrice);
		}
	}
	@Transactional
	public void saveGuideLineDatePriceByLineNo(List<GuideLineDatePrice> guideLineDatePriceList,String lineNo){
		//delete 
		guideLineDatePriceMapper.deleteGuideLineDatePriceByLineNo(lineNo);
		//save
		for (GuideLineDatePrice guideLineDatePrice : guideLineDatePriceList) {
			guideLineDatePriceMapper.createGuideLineDatePriceSelective(guideLineDatePrice);
		}
		
	}
}