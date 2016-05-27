package com.mlx.guide.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mlx.guide.constant.Const;
import com.mlx.guide.constant.EAuditStatus;
import com.mlx.guide.constant.EFlag;
import com.mlx.guide.constant.ELineType;
import com.mlx.guide.constant.EStatus;
import com.mlx.guide.constant.ExceptionCode;
import com.mlx.guide.constant.JsonResult;
import com.mlx.guide.entity.GuideLine;
import com.mlx.guide.entity.GuideLineDatePrice;
import com.mlx.guide.entity.GuideLineTrip;
import com.mlx.guide.model.GuideLineTripModel;
import com.mlx.guide.service.GuideLineDatePriceService;
import com.mlx.guide.service.GuideLineService;
import com.mlx.guide.service.GuideLineTripService;

@Controller
@RequestMapping(value = "/admin/trip")
public class GuideLineTripAdminController {

		private static Logger logger = LoggerFactory.getLogger(GuideLineTripAdminController.class);
		@Autowired
		private GuideLineTripService guideLineTripService;
		@Autowired
		private GuideLineService guideLineService;
		@Autowired
		private GuideLineDatePriceService guideLineDatePriceService;

		/**
		 * 读取公共的参数值和设置,根据界面设置的参数值来选择页面菜单选中效果
		 * 
		 * @param menuBar
		 * @param model
		 */
		@ModelAttribute
		public void common(Model model) {
			model.addAttribute("productclass", Const.MENU_FIRST);
			model.addAttribute("product_lineclass", Const.MENU_SUB);
			model.addAttribute("EStatus", EStatus.getMap());
			model.addAttribute("EAuditStatus", EAuditStatus.getMap());
		}

		/**
		 * 列表
		 * 
		 * @param lineNo
		 * @param guideLineTrip
		 * @param model
		 * @return
		 */
		@RequestMapping()
		public String list(@RequestParam String lineNo, @RequestParam String startDate, @RequestParam String endDate,
				GuideLineTrip guideLineTrip, Model model) {
			try {
				guideLineTrip.setLineNo(lineNo);
				guideLineTrip.setFlag(EFlag.VALID.getId().byteValue());
				List<GuideLineTrip> list = guideLineTripService.getGuideLineTripPageList(guideLineTrip);
				model.addAttribute("list", list);
				model.addAttribute("lineNo", lineNo);
				model.addAttribute("startDate", startDate);
				model.addAttribute("endDate", endDate);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}

			return "admin/line/lineTrip";
		}

		/**
		 * 编辑行程
		 * 
		 * @param lineNo
		 * @param guideLineTrip
		 * @param model
		 * @return
		 */
		@RequestMapping(value = "edit/{lineNo}")
		public String edit(@PathVariable String lineNo, GuideLineTrip guideLineTrip, Model model) {
			try {
				List<GuideLineTrip> list = guideLineTripService.getGuideLineTripPageList(guideLineTrip);
				model.addAttribute("list", list);
				model.addAttribute("lineNo", lineNo);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			return "admin/line/lineTrip";
		}

		/**
		 * 新增或修改
		 * 
		 * @param guideLineTripModel
		 * @param model
		 * @return
		 */
		@RequestMapping(value = "/save")
		public String add(GuideLineTripModel guideLineTripModel, @RequestParam(value = "lineNo") String lineNo,
				String startDate,String endDate,Model model ) {
			try {

				model.addAttribute("lineNo", lineNo);
				// 线路
				GuideLine line = guideLineService.getGuideLineByLineNo(lineNo);
				model.addAttribute("line", line);
				model.addAttribute("ELineType", ELineType.getMap());
				// 价格
				List<GuideLineDatePrice> lsGuideLineDatePrices = guideLineDatePriceService
						.getGuideLineDatePriceByLineNo(lineNo);
				model.addAttribute("lsPrices", lsGuideLineDatePrices);
				// 新增或更新行程
				guideLineTripService.updateBitchSelective(guideLineTripModel.getGuideLineTrips());
				// 行程
				GuideLineTrip guideLineTrip = new GuideLineTrip();
				guideLineTrip.setLineNo(lineNo);
				List<GuideLineTrip> trips = guideLineTripService.getGuideLineTripPageList(guideLineTrip);
				model.addAttribute("trips", trips);
				model.addAttribute("startDate",startDate);
				model.addAttribute("endDate",endDate);
				

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			return "admin/line/submit";
		}

		/**
		 * 发布
		 * 
		 * @param lineNo
		 * @param guideLine
		 * @param model
		 * @return
		 */
		@RequestMapping(value = "/preView/{lineNo}")
		public String submit(@PathVariable(value = "lineNo") String lineNo, GuideLine guideLine, Model model) {
			// 审核状态改为1待审核
			try {
				guideLine.setAuditStatus(EAuditStatus.AUDIT_ON.getId());
				guideLineService.updateGuideLineSelective(guideLine);
				model.addAttribute("lineNo", lineNo);
				
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				
			}
			return "redirect:/admin/guideLine";
		}

		/**
		 * 删除
		 * 
		 * @param id
		 * @return
		 */
		@RequestMapping(value = "delete/{id}")
		@ResponseBody
		public JsonResult delete(@PathVariable Integer id) {
			try {
				guideLineTripService.deleteByPrimaryKey(new Long(id));
				return new JsonResult(ExceptionCode.SUCCESSFUL);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				return new JsonResult(ExceptionCode.FAIL);
			}
		}

}
