package com.mlx.guide.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.dao.WxEventPushMapper;
import com.mlx.guide.entity.WxEventPush;

@Service
@Transactional
public class WxEventPushService {

	@Autowired
	private WxEventPushMapper wxEventPushMapper;

	public List<WxEventPush> getWxEventPushList() {
		return wxEventPushMapper.getWxEventPushList();
	}

	public PageList<WxEventPush> getWxEventPushList(PageBounds pageBounds) {
		return wxEventPushMapper.getWxEventPushList(pageBounds);
	}

	public List<WxEventPush> getWxEventPushPageList(WxEventPush wxEventPush) {
		return wxEventPushMapper.getWxEventPushPageList(wxEventPush);
	}

	public PageList<WxEventPush> getWxEventPushPageList(WxEventPush wxEventPush, PageBounds pageBounds) {
		return wxEventPushMapper.getWxEventPushPageList(wxEventPush, pageBounds);
	}

	public List<WxEventPush> getWxEventPushPageListByMap(Map<String, Object> map) {
		return wxEventPushMapper.getWxEventPushPageListByMap(map);
	}

	public PageList<WxEventPush> getWxEventPushPageListByMap(Map<String, Object> map, PageBounds pageBounds) {
		return wxEventPushMapper.getWxEventPushPageListByMap(map, pageBounds);
	}

	public WxEventPush getWxEventPushByPrimaryKey(Long id) {
		return wxEventPushMapper.getWxEventPushByPrimaryKey(id);
	}

	public void createWxEventPush(WxEventPush wxEventPush) {
		wxEventPushMapper.createWxEventPush(wxEventPush);
	}

	public void createWxEventPushSelective(WxEventPush wxEventPush) {
		wxEventPushMapper.createWxEventPushSelective(wxEventPush);
	}

	public void createWxEventPushBitch(List<WxEventPush> wxEventPushList) {
		for (WxEventPush wxEventPush : wxEventPushList) {
			wxEventPushMapper.createWxEventPush(wxEventPush);
		}
	}

	public void updateWxEventPush(WxEventPush wxEventPush) {
		wxEventPushMapper.updateWxEventPush(wxEventPush);
	}

	public void updateWxEventPushBitch(List<WxEventPush> wxEventPushList) {
		for (WxEventPush wxEventPush : wxEventPushList) {
			wxEventPushMapper.updateWxEventPush(wxEventPush);
		}
	}

	public void updateWxEventPushSelective(WxEventPush wxEventPush) {
		wxEventPushMapper.updateWxEventPushSelective(wxEventPush);
	}

	public void updateWxEventPushSelectiveBitch(List<WxEventPush> wxEventPushList) {
		for (WxEventPush wxEventPush : wxEventPushList) {
			wxEventPushMapper.updateWxEventPushSelective(wxEventPush);
		}
	}

	public void deleteWxEventPush(Long id) {
		wxEventPushMapper.deleteWxEventPush(id);
	}

	public void deleteWxEventPushBitch(List<Long> idList) {
		for (Long id : idList) {
			wxEventPushMapper.deleteWxEventPush(id);
		}
	}

}