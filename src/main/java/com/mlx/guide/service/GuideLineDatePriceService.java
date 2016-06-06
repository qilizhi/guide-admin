package com.mlx.guide.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.constant.EGoodsType;
import com.mlx.guide.constant.EProductNoPrefix;
import com.mlx.guide.constant.ETuanStatus;
import com.mlx.guide.dao.GuideLineDatePriceMapper;
import com.mlx.guide.dao.GuideLineTripMapper;
import com.mlx.guide.dao.GuideTuanMapper;
import com.mlx.guide.entity.GuideLine;
import com.mlx.guide.entity.GuideLineDatePrice;
import com.mlx.guide.entity.GuideLineTrip;
import com.mlx.guide.entity.GuideService;
import com.mlx.guide.entity.GuideTuan;
import com.mlx.guide.util.StringUtil;

@Service
@Transactional
public class GuideLineDatePriceService {

	Logger logger = LoggerFactory.getLogger(GuideLineDatePriceService.class);

	@Autowired
	private GuideLineDatePriceMapper guideLineDatePriceMapper;
	@Autowired
	private GuideTuanMapper guideTuanMapper;
	@Autowired
	private GuideLineTripMapper guideLineTripMapper;
	@Autowired
	private GuideLineService guideLineService;
	@Autowired
	private GuideLineTripService guideLineTripService;
	
	@Autowired
	private GuideServiceService guideSService;

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
	public void saveGuideLineDatePriceByLineNo(List<GuideLineDatePrice> guideLineDatePriceList, String lineNo) {
		// delete
		guideLineDatePriceMapper.deleteGuideLineDatePriceByLineNo(lineNo);
		// 查询线路
		GuideLine line = guideLineService.getGuideLineByLineNo(lineNo);
		// save
		for (GuideLineDatePrice guideLineDatePrice : guideLineDatePriceList) {
			guideLineDatePrice.setNum(line.getNum());
			GuideTuan gt = new GuideTuan();
			// 根据时间及产品编号查询。是否这天有团
			gt.setTuanDate(guideLineDatePrice.getLineDate());
			gt.setGoodsNo(guideLineDatePrice.getLineNo());
			List<GuideTuan> gts = guideTuanMapper.getGuideTuanPageList(gt);
			if (gts.size() <= 0) {// 没有则插入
				gt.setCreateTime(new Date());
				gt.setGoodsType(EGoodsType.LINE.getCode());
				gt.setTuanStatus(ETuanStatus.TOUR.getId().byteValue());
				gt.setTuanNo(StringUtil.generateProductSerialNumber(EProductNoPrefix.Line.getPrefix()));
				gt.setName(line.getTitle());
				gt.setFullNum(line.getNum());
				gt.setUserNo(line.getUserNo());
				gt.setUserName(line.getUserName());
				guideTuanMapper.insertSelective(gt);

			} else {
				for (GuideTuan g : gts) {
					if (!g.getTuanStatus().equals(ETuanStatus.TOURED.getId().byteValue())) {
						gt.setId(g.getId());
						gt.setCreateTime(new Date());
						gt.setGoodsType(EGoodsType.LINE.getCode());
						gt.setTuanStatus(ETuanStatus.TOUR.getId().byteValue());
						gt.setName(line.getTitle());
						gt.setFullNum(line.getNum());
						gt.setUserNo(line.getUserNo());
						gt.setUserName(line.getUserName());
						guideTuanMapper.updateByPrimaryKeySelective(gt);
					} else {

						logger.info(
								"这个团已出团不能修改：" + g.getTuanNo() + "产品编号：" + g.getGoodsNo() + "类型：" + g.getGoodsType());
					}

				}
			}
			guideLineDatePriceMapper.createGuideLineDatePriceSelective(guideLineDatePrice);
		}

	}
	@Transactional
	public void saveGuideLineDatePriceByServiceNo(List<GuideLineDatePrice> guideLineDatePriceList, String serviceNo) {
		// delete
		guideLineDatePriceMapper.deleteGuideLineDatePriceByLineNo(serviceNo);
		// 查询线路
		GuideService guideSevice = guideSService.getGuideServiceByServiceNo(serviceNo);
		// save
		for (GuideLineDatePrice guideLineDatePrice : guideLineDatePriceList) {
			guideLineDatePrice.setNum(guideSevice.getNum());
			GuideTuan gt = new GuideTuan();
			// 根据时间及产品编号查询。是否这天有团
			gt.setTuanDate(guideLineDatePrice.getLineDate());
			gt.setGoodsNo(guideLineDatePrice.getLineNo());
			List<GuideTuan> gts = guideTuanMapper.getGuideTuanPageList(gt);
			if (gts.size() <= 0) {// 没有则插入
				gt.setCreateTime(new Date());
				gt.setGoodsType(EGoodsType.LOCAL.getCode());
				gt.setTuanStatus(ETuanStatus.TOUR.getId().byteValue());
				gt.setTuanNo(StringUtil.generateProductSerialNumber(EProductNoPrefix.Line.getPrefix()));
				gt.setName(guideSevice.getTitle());
				gt.setFullNum(guideSevice.getNum());
				gt.setUserNo(guideSevice.getUserNo());
				gt.setUserName(guideSevice.getUserName());
				guideTuanMapper.insertSelective(gt);
				
			} else {
				for (GuideTuan g : gts) {
					if (!g.getTuanStatus().equals(ETuanStatus.TOURED.getId().byteValue())) {
						gt.setId(g.getId());
						gt.setCreateTime(new Date());
						gt.setGoodsType(EGoodsType.LOCAL.getCode());
						gt.setTuanStatus(ETuanStatus.TOUR.getId().byteValue());
						gt.setName(guideSevice.getTitle());
						gt.setFullNum(guideSevice.getNum());
						gt.setUserNo(guideSevice.getUserNo());
						gt.setUserName(guideSevice.getUserName());
						guideTuanMapper.updateByPrimaryKeySelective(gt);
					} else {
						
						logger.info(
								"这个团已出团不能修改：" + g.getTuanNo() + "产品编号：" + g.getGoodsNo() + "类型：" + g.getGoodsType());
					}
					
				}
			}
			guideLineDatePriceMapper.createGuideLineDatePriceSelective(guideLineDatePrice);
		}
		
	}
}