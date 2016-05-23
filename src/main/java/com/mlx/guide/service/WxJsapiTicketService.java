package com.mlx.guide.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.dao.WxJsapiTicketMapper;
import com.mlx.guide.entity.WxJsapiTicket;

@Service
@Transactional
public class WxJsapiTicketService {

	@Autowired
	private WxJsapiTicketMapper wxJsapiTicketMapper;

	public List<WxJsapiTicket> getWxJsapiTicketList() {
		return wxJsapiTicketMapper.getWxJsapiTicketList();
	}

	public PageList<WxJsapiTicket> getWxJsapiTicketList(PageBounds pageBounds) {
		return wxJsapiTicketMapper.getWxJsapiTicketList(pageBounds);
	}

	public List<WxJsapiTicket> getWxJsapiTicketPageList(WxJsapiTicket wxJsapiTicket) {
		return wxJsapiTicketMapper.getWxJsapiTicketPageList(wxJsapiTicket);
	}

	public PageList<WxJsapiTicket> getWxJsapiTicketPageList(WxJsapiTicket wxJsapiTicket, PageBounds pageBounds) {
		return wxJsapiTicketMapper.getWxJsapiTicketPageList(wxJsapiTicket, pageBounds);
	}

	public List<WxJsapiTicket> getWxJsapiTicketPageListByMap(Map<String, Object> map) {
		return wxJsapiTicketMapper.getWxJsapiTicketPageListByMap(map);
	}

	public PageList<WxJsapiTicket> getWxJsapiTicketPageListByMap(Map<String, Object> map, PageBounds pageBounds) {
		return wxJsapiTicketMapper.getWxJsapiTicketPageListByMap(map, pageBounds);
	}

	public WxJsapiTicket getWxJsapiTicketByPrimaryKey(Long id) {
		return wxJsapiTicketMapper.getWxJsapiTicketByPrimaryKey(id);
	}

	public void createWxJsapiTicket(WxJsapiTicket wxJsapiTicket) {
		wxJsapiTicketMapper.createWxJsapiTicket(wxJsapiTicket);
	}

	public void createWxJsapiTicketSelective(WxJsapiTicket wxJsapiTicket) {
		wxJsapiTicketMapper.createWxJsapiTicketSelective(wxJsapiTicket);
	}

	public void createWxJsapiTicketBitch(List<WxJsapiTicket> wxJsapiTicketList) {
		for (WxJsapiTicket wxJsapiTicket : wxJsapiTicketList) {
			wxJsapiTicketMapper.createWxJsapiTicket(wxJsapiTicket);
		}
	}

	public void updateWxJsapiTicket(WxJsapiTicket wxJsapiTicket) {
		wxJsapiTicketMapper.updateWxJsapiTicket(wxJsapiTicket);
	}

	public void updateWxJsapiTicketBitch(List<WxJsapiTicket> wxJsapiTicketList) {
		for (WxJsapiTicket wxJsapiTicket : wxJsapiTicketList) {
			wxJsapiTicketMapper.updateWxJsapiTicket(wxJsapiTicket);
		}
	}

	public void updateWxJsapiTicketSelective(WxJsapiTicket wxJsapiTicket) {
		wxJsapiTicketMapper.updateWxJsapiTicketSelective(wxJsapiTicket);
	}

	public void updateWxJsapiTicketSelectiveBitch(List<WxJsapiTicket> wxJsapiTicketList) {
		for (WxJsapiTicket wxJsapiTicket : wxJsapiTicketList) {
			wxJsapiTicketMapper.updateWxJsapiTicketSelective(wxJsapiTicket);
		}
	}

	public void deleteWxJsapiTicket(Long id) {
		wxJsapiTicketMapper.deleteWxJsapiTicket(id);
	}

	public void deleteWxJsapiTicketBitch(List<Long> idList) {
		for (Long id : idList) {
			wxJsapiTicketMapper.deleteWxJsapiTicket(id);
		}
	}

}