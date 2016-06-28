package com.mlx.guide.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mlx.guide.constant.EGoodsType;
import com.mlx.guide.constant.EProductNoPrefix;
import com.mlx.guide.constant.ETuanStatus;
import com.mlx.guide.dao.GuideLineTripMapper;
import com.mlx.guide.dao.GuideTuanMapper;
import com.mlx.guide.entity.GuideLine;
import com.mlx.guide.entity.GuideService;
import com.mlx.guide.entity.GuideTuan;
import com.mlx.guide.util.StringUtil;

@Transactional
@Service
public class GuideLineDatePriceService {

	Logger logger = LoggerFactory.getLogger(GuideLineDatePriceService.class);

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

	@Transactional
	public void saveGuideLineDatePriceByLineNo(List<GuideTuan> guideLineDatePriceList, String lineNo) {
		// delete
		// guideLineDatePriceMapper.deleteGuideLineDatePriceByLineNo(lineNo);
		// 查询线路
		GuideLine line = guideLineService.getGuideLineByLineNo(lineNo);
		// save
		for (GuideTuan guideLineDatePrice : guideLineDatePriceList) {
			if (guideLineDatePrice.getTuanNo() == null || guideLineDatePrice.getTuanNo().equals("")) {
				guideLineDatePrice.setTuanNo(StringUtil.generateProductSerialNumber(EProductNoPrefix.Tuan.getPrefix()));
			}

			guideLineDatePrice.setGoodsType(EGoodsType.LINE.getCode());
			guideLineDatePrice.setCreateTime(new Date());
			guideLineDatePrice.setTuanStatus(ETuanStatus.TOUR.getId().byteValue());
			
			
			//guideLineDatePrice.setNum(line.getNum());
			//GuideTuan gt = new GuideTuan();
			// 根据时间及产品编号查询。是否这天有团
		/*	 gt.setTuanDate(guideLineDatePrice.getTuanDate());
			 gt.setGoodsNo(guideLineDatePrice.getGoodsNo());
			//gt.setTuanNo(guideLineDatePrice.getTuanNo());
			List<GuideTuan> gts = guideTuanMapper.getGuideTuanPageList(gt);

			if (gts.size() <= 0) {// 没有则插入
				gt.setCreateTime(new Date());
				gt.setGoodsType(EGoodsType.LINE.getCode());
				gt.setTuanStatus(ETuanStatus.TOUR.getId().byteValue());
				gt.setTuanNo(guideLineDatePrice.getTuanNo());
				gt.setName(line.getTitle());
				gt.setFullNum(line.getNum());
				gt.setUserNo(line.getUserNo());
				gt.setUserName(line.getUserName());
				gt.setTuanDate(guideLineDatePrice.getTuanDate());
				gt.setGoodsNo(line.getLineNo());
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
						gt.setTuanDate(guideLineDatePrice.getTuanDate());
						gt.setGoodsNo(line.getLineNo());
						guideTuanMapper.updateByPrimaryKeySelective(gt);
					} else {

						logger.info(
								"这个团已出团不能修改：" + g.getTuanNo() + "产品编号：" + g.getGoodsNo() + "类型：" + g.getGoodsType());
					}

				}
			}*/
			if (guideLineDatePrice.getId() != null) {
				
				
				guideTuanMapper.updateByPrimaryKeySelective(guideLineDatePrice);
			} else {
				guideTuanMapper.insertSelective(guideLineDatePrice);
			}
		}

	}

	@Transactional
	public void saveGuideLineDatePriceByServiceNo(List<GuideTuan> guideLineDatePriceList, String serviceNo) {
		// delete
		// guideTuanMapper.deleteGuideLineDatePriceByLineNo(serviceNo);
		// 查询线路
		GuideService guideSevice = guideSService.getGuideServiceByServiceNo(serviceNo);
		// save
		for (GuideTuan guideLineDatePrice : guideLineDatePriceList) {
			guideLineDatePrice.setNum(guideSevice.getNum());
			GuideTuan gt = new GuideTuan();
			// 根据时间及产品编号查询。是否这天有团
			gt.setTuanDate(guideLineDatePrice.getTuanDate());
			gt.setGoodsNo(guideLineDatePrice.getGoodsNo());
			List<GuideTuan> gts = guideTuanMapper.getGuideTuanPageList(gt);
			if (gts.size() <= 0) {// 没有则插入
				gt.setCreateTime(new Date());
				gt.setGoodsType(EGoodsType.LOCAL.getCode());
				gt.setTuanStatus(ETuanStatus.TOUR.getId().byteValue());
				gt.setTuanNo(StringUtil.generateProductSerialNumber(EProductNoPrefix.Tuan.getPrefix()));
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
			guideTuanMapper.insertSelective(guideLineDatePrice);
		}

	}

	public List<GuideTuan> getGuideLineDatePriceByGoodsNo(String lineNo) {
		GuideTuan gt = new GuideTuan();
		gt.setGoodsNo(lineNo);
		return guideTuanMapper.getGuideTuanPageList(gt);
	}

	public void saveGuideLineDatePriceBitch(List<GuideTuan> guideLineDatePriceList) {
		for (GuideTuan guideLineDatePrice : guideLineDatePriceList) {
			if (guideLineDatePrice.getId() != null) {
				// guideLineDatePriceMapper.updateGuideLineDatePrice(guideLineDatePrice);
				guideTuanMapper.updateByPrimaryKeySelective(guideLineDatePrice);
				continue;
			}
			guideTuanMapper.insertSelective(guideLineDatePrice);
		}
	}

	public void deleteGuideLineDatePriceByDate(Map<String, Object> map) {
		guideTuanMapper.deleteGuideLineDatePriceByDate(map);

	}
}