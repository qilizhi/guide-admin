package com.mlx.guide.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.dao.WxTwoDimensionalCodeMapper;
import com.mlx.guide.entity.WxTwoDimensionalCode;

@Service
@Transactional
public class WxTwoDimensionalCodeService {

	@Autowired
	private WxTwoDimensionalCodeMapper wxTwoDimensionalCodeMapper;

	public List<WxTwoDimensionalCode> getWxTwoDimensionalCodeList() {
		return wxTwoDimensionalCodeMapper.getWxTwoDimensionalCodeList();
	}

	public PageList<WxTwoDimensionalCode> getWxTwoDimensionalCodeList(PageBounds pageBounds) {
		return wxTwoDimensionalCodeMapper.getWxTwoDimensionalCodeList(pageBounds);
	}

	public List<WxTwoDimensionalCode> getWxTwoDimensionalCodePageList(WxTwoDimensionalCode wxTwoDimensionalCode) {
		return wxTwoDimensionalCodeMapper.getWxTwoDimensionalCodePageList(wxTwoDimensionalCode);
	}

	public PageList<WxTwoDimensionalCode> getWxTwoDimensionalCodePageList(WxTwoDimensionalCode wxTwoDimensionalCode,
			PageBounds pageBounds) {
		return wxTwoDimensionalCodeMapper.getWxTwoDimensionalCodePageList(wxTwoDimensionalCode, pageBounds);
	}

	public List<WxTwoDimensionalCode> getWxTwoDimensionalCodePageListByMap(Map<String, Object> map) {
		return wxTwoDimensionalCodeMapper.getWxTwoDimensionalCodePageListByMap(map);
	}

	public PageList<WxTwoDimensionalCode> getWxTwoDimensionalCodePageListByMap(Map<String, Object> map,
			PageBounds pageBounds) {
		return wxTwoDimensionalCodeMapper.getWxTwoDimensionalCodePageListByMap(map, pageBounds);
	}

	public WxTwoDimensionalCode getWxTwoDimensionalCodeByPrimaryKey(Long id) {
		return wxTwoDimensionalCodeMapper.getWxTwoDimensionalCodeByPrimaryKey(id);
	}

	public void createWxTwoDimensionalCode(WxTwoDimensionalCode wxTwoDimensionalCode) {
		wxTwoDimensionalCodeMapper.createWxTwoDimensionalCode(wxTwoDimensionalCode);
	}

	public void createWxTwoDimensionalCodeSelective(WxTwoDimensionalCode wxTwoDimensionalCode) {
		wxTwoDimensionalCodeMapper.createWxTwoDimensionalCodeSelective(wxTwoDimensionalCode);
	}

	public void createWxTwoDimensionalCodeBitch(List<WxTwoDimensionalCode> wxTwoDimensionalCodeList) {
		for (WxTwoDimensionalCode wxTwoDimensionalCode : wxTwoDimensionalCodeList) {
			wxTwoDimensionalCodeMapper.createWxTwoDimensionalCode(wxTwoDimensionalCode);
		}
	}

	public void updateWxTwoDimensionalCode(WxTwoDimensionalCode wxTwoDimensionalCode) {
		wxTwoDimensionalCodeMapper.updateWxTwoDimensionalCode(wxTwoDimensionalCode);
	}

	public void updateWxTwoDimensionalCodeBitch(List<WxTwoDimensionalCode> wxTwoDimensionalCodeList) {
		for (WxTwoDimensionalCode wxTwoDimensionalCode : wxTwoDimensionalCodeList) {
			wxTwoDimensionalCodeMapper.updateWxTwoDimensionalCode(wxTwoDimensionalCode);
		}
	}

	public void updateWxTwoDimensionalCodeSelective(WxTwoDimensionalCode wxTwoDimensionalCode) {
		wxTwoDimensionalCodeMapper.updateWxTwoDimensionalCodeSelective(wxTwoDimensionalCode);
	}

	public void updateWxTwoDimensionalCodeSelectiveBitch(List<WxTwoDimensionalCode> wxTwoDimensionalCodeList) {
		for (WxTwoDimensionalCode wxTwoDimensionalCode : wxTwoDimensionalCodeList) {
			wxTwoDimensionalCodeMapper.updateWxTwoDimensionalCodeSelective(wxTwoDimensionalCode);
		}
	}

	public void deleteWxTwoDimensionalCode(Long id) {
		wxTwoDimensionalCodeMapper.deleteWxTwoDimensionalCode(id);
	}

	public void deleteWxTwoDimensionalCodeBitch(List<Long> idList) {
		for (Long id : idList) {
			wxTwoDimensionalCodeMapper.deleteWxTwoDimensionalCode(id);
		}
	}

}