package com.mlx.guide.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.entity.WxEventPush;

public interface WxEventPushMapper {

	public List<WxEventPush> getWxEventPushList();

	public PageList<WxEventPush> getWxEventPushList(PageBounds pageBounds);

	public List<WxEventPush> getWxEventPushPageList(WxEventPush wxEventPush);

	public PageList<WxEventPush> getWxEventPushPageList(WxEventPush wxEventPush, PageBounds pageBounds);

	public List<WxEventPush> getWxEventPushPageListByMap(Map<String, Object> map);

	public PageList<WxEventPush> getWxEventPushPageListByMap(Map<String, Object> map, PageBounds pageBounds);

	public WxEventPush getWxEventPushByPrimaryKey(Long id);

	public void createWxEventPush(WxEventPush wxEventPush);

	public void createWxEventPushSelective(WxEventPush wxEventPush);

	public void updateWxEventPush(WxEventPush wxEventPush);

	public void updateWxEventPushSelective(WxEventPush wxEventPush);

	public void deleteWxEventPush(Long id);
}