package com.mlx.guide.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.entity.WxEventPushArticle;

public interface WxEventPushArticleMapper {

	public List<WxEventPushArticle> getWxEventPushArticleList();

	public PageList<WxEventPushArticle> getWxEventPushArticleList(PageBounds pageBounds);

	public List<WxEventPushArticle> getWxEventPushArticlePageList(WxEventPushArticle wxEventPushArticle);

	public PageList<WxEventPushArticle> getWxEventPushArticlePageList(WxEventPushArticle wxEventPushArticle,
			PageBounds pageBounds);

	public List<WxEventPushArticle> getWxEventPushArticlePageListByMap(Map<String, Object> map);

	public PageList<WxEventPushArticle> getWxEventPushArticlePageListByMap(Map<String, Object> map,
			PageBounds pageBounds);

	public WxEventPushArticle getWxEventPushArticleByPrimaryKey(Long id);

	public void createWxEventPushArticle(WxEventPushArticle wxEventPushArticle);

	public void createWxEventPushArticleSelective(WxEventPushArticle wxEventPushArticle);

	public void updateWxEventPushArticle(WxEventPushArticle wxEventPushArticle);

	public void updateWxEventPushArticleSelective(WxEventPushArticle wxEventPushArticle);

	public void deleteWxEventPushArticle(Long id);
}