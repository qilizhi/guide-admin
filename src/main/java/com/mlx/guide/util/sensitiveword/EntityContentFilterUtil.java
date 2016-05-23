package com.mlx.guide.util.sensitiveword;

import com.mlx.guide.entity.GuideLine;

/**
 * 实体类属性内容过滤
 * 
 * @author quan
 *
 */
public final class EntityContentFilterUtil {

	/**
	 * 敏感词过滤
	 * 
	 * @param info
	 */
/*	public static void sensitivewordFilter(ActInfo info) {
		info.setActName(WordFilterUtil.filterText(info.getActName()));
		info.setRemark(WordFilterUtil.filterText(info.getRemark()));
	}*/

	/**
	 * 敏感词过滤
	 * 
	 * @param info
	 */
/*	public static void sensitivewordFilter(GuideLog info) {
		info.setContent(WordFilterUtil.filterText(info.getContent()));
	}*/

	/**
	 * 敏感词过滤
	 * 
	 * @param info
	 */
	public static void sensitivewordFilter(GuideLine info) {
		info.setContent(WordFilterUtil.filterText(info.getContent()));
		info.setDescription(WordFilterUtil.filterText(info.getDescription()));
		info.setRemark(WordFilterUtil.filterText(info.getRemark()));
		info.setTitle(WordFilterUtil.filterText(info.getTitle()));
	}
}
