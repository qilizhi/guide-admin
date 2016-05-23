package com.mlx.guide.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.dao.WxEventPushArticleMapper;
import com.mlx.guide.entity.WxEventPushArticle;

@Service
@Transactional
public class WxEventPushArticleService {

	@Autowired
	private WxEventPushArticleMapper wxEventPushArticleMapper;

	public List<WxEventPushArticle> getWxEventPushArticleList() {
		return wxEventPushArticleMapper.getWxEventPushArticleList();
	}

	public PageList<WxEventPushArticle> getWxEventPushArticleList(PageBounds pageBounds) {
		return wxEventPushArticleMapper.getWxEventPushArticleList(pageBounds);
	}

	public List<WxEventPushArticle> getWxEventPushArticlePageList(WxEventPushArticle wxEventPushArticle) {
		return wxEventPushArticleMapper.getWxEventPushArticlePageList(wxEventPushArticle);
	}

	public PageList<WxEventPushArticle> getWxEventPushArticlePageList(WxEventPushArticle wxEventPushArticle,
			PageBounds pageBounds) {
		return wxEventPushArticleMapper.getWxEventPushArticlePageList(wxEventPushArticle, pageBounds);
	}

	public List<WxEventPushArticle> getWxEventPushArticlePageListByMap(Map<String, Object> map) {
		return wxEventPushArticleMapper.getWxEventPushArticlePageListByMap(map);
	}

	public PageList<WxEventPushArticle> getWxEventPushArticlePageListByMap(Map<String, Object> map,
			PageBounds pageBounds) {
		return wxEventPushArticleMapper.getWxEventPushArticlePageListByMap(map, pageBounds);
	}

	public WxEventPushArticle getWxEventPushArticleByPrimaryKey(Long id) {
		return wxEventPushArticleMapper.getWxEventPushArticleByPrimaryKey(id);
	}

	public void createWxEventPushArticle(WxEventPushArticle wxEventPushArticle) {
		wxEventPushArticleMapper.createWxEventPushArticle(wxEventPushArticle);
	}

	public void createWxEventPushArticleSelective(WxEventPushArticle wxEventPushArticle) {
		wxEventPushArticleMapper.createWxEventPushArticleSelective(wxEventPushArticle);
	}

	public void createWxEventPushArticleBitch(List<WxEventPushArticle> wxEventPushArticleList) {
		for (WxEventPushArticle wxEventPushArticle : wxEventPushArticleList) {
			wxEventPushArticleMapper.createWxEventPushArticle(wxEventPushArticle);
		}
	}

	public void updateWxEventPushArticle(WxEventPushArticle wxEventPushArticle) {
		wxEventPushArticleMapper.updateWxEventPushArticle(wxEventPushArticle);
	}

	public void updateWxEventPushArticleBitch(List<WxEventPushArticle> wxEventPushArticleList) {
		for (WxEventPushArticle wxEventPushArticle : wxEventPushArticleList) {
			wxEventPushArticleMapper.updateWxEventPushArticle(wxEventPushArticle);
		}
	}

	public void updateWxEventPushArticleSelective(WxEventPushArticle wxEventPushArticle) {
		wxEventPushArticleMapper.updateWxEventPushArticleSelective(wxEventPushArticle);
	}

	public void updateWxEventPushArticleSelectiveBitch(List<WxEventPushArticle> wxEventPushArticleList) {
		for (WxEventPushArticle wxEventPushArticle : wxEventPushArticleList) {
			wxEventPushArticleMapper.updateWxEventPushArticleSelective(wxEventPushArticle);
		}
	}

	public void deleteWxEventPushArticle(Long id) {
		wxEventPushArticleMapper.deleteWxEventPushArticle(id);
	}

	public void deleteWxEventPushArticleBitch(List<Long> idList) {
		for (Long id : idList) {
			wxEventPushArticleMapper.deleteWxEventPushArticle(id);
		}
	}

}