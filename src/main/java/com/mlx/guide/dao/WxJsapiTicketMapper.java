package com.mlx.guide.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.entity.WxJsapiTicket;

public interface WxJsapiTicketMapper {

	public List<WxJsapiTicket> getWxJsapiTicketList();

	public PageList<WxJsapiTicket> getWxJsapiTicketList(PageBounds pageBounds);

	public List<WxJsapiTicket> getWxJsapiTicketPageList(WxJsapiTicket wxJsapiTicket);

	public PageList<WxJsapiTicket> getWxJsapiTicketPageList(WxJsapiTicket wxJsapiTicket, PageBounds pageBounds);

	public List<WxJsapiTicket> getWxJsapiTicketPageListByMap(Map<String, Object> map);

	public PageList<WxJsapiTicket> getWxJsapiTicketPageListByMap(Map<String, Object> map, PageBounds pageBounds);

	public WxJsapiTicket getWxJsapiTicketByPrimaryKey(Long id);

	public void createWxJsapiTicket(WxJsapiTicket wxJsapiTicket);

	public void createWxJsapiTicketSelective(WxJsapiTicket wxJsapiTicket);

	public void updateWxJsapiTicket(WxJsapiTicket wxJsapiTicket);

	public void updateWxJsapiTicketSelective(WxJsapiTicket wxJsapiTicket);

	public void deleteWxJsapiTicket(Long id);
}