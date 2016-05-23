package com.mlx.guide.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.dao.WxAccessTokenMapper;
import com.mlx.guide.entity.WxAccessToken;

@Service
@Transactional
public class WxAccessTokenService {

	@Autowired
	private WxAccessTokenMapper wxAccessTokenMapper;

	public List<WxAccessToken> getWxAccessTokenList() {
		return wxAccessTokenMapper.getWxAccessTokenList();
	}

	public PageList<WxAccessToken> getWxAccessTokenList(PageBounds pageBounds) {
		return wxAccessTokenMapper.getWxAccessTokenList(pageBounds);
	}

	public List<WxAccessToken> getWxAccessTokenPageList(WxAccessToken wxAccessToken) {
		return wxAccessTokenMapper.getWxAccessTokenPageList(wxAccessToken);
	}

	public PageList<WxAccessToken> getWxAccessTokenPageList(WxAccessToken wxAccessToken, PageBounds pageBounds) {
		return wxAccessTokenMapper.getWxAccessTokenPageList(wxAccessToken, pageBounds);
	}

	public List<WxAccessToken> getWxAccessTokenPageListByMap(Map<String, Object> map) {
		return wxAccessTokenMapper.getWxAccessTokenPageListByMap(map);
	}

	public PageList<WxAccessToken> getWxAccessTokenPageListByMap(Map<String, Object> map, PageBounds pageBounds) {
		return wxAccessTokenMapper.getWxAccessTokenPageListByMap(map, pageBounds);
	}

	public WxAccessToken getWxAccessTokenByPrimaryKey(Long id) {
		return wxAccessTokenMapper.getWxAccessTokenByPrimaryKey(id);
	}

	public void createWxAccessToken(WxAccessToken wxAccessToken) {
		wxAccessTokenMapper.createWxAccessToken(wxAccessToken);
	}

	public void createWxAccessTokenSelective(WxAccessToken wxAccessToken) {
		wxAccessTokenMapper.createWxAccessTokenSelective(wxAccessToken);
	}

	public void createWxAccessTokenBitch(List<WxAccessToken> wxAccessTokenList) {
		for (WxAccessToken wxAccessToken : wxAccessTokenList) {
			wxAccessTokenMapper.createWxAccessToken(wxAccessToken);
		}
	}

	public void updateWxAccessToken(WxAccessToken wxAccessToken) {
		wxAccessTokenMapper.updateWxAccessToken(wxAccessToken);
	}

	public void updateWxAccessTokenBitch(List<WxAccessToken> wxAccessTokenList) {
		for (WxAccessToken wxAccessToken : wxAccessTokenList) {
			wxAccessTokenMapper.updateWxAccessToken(wxAccessToken);
		}
	}

	public void updateWxAccessTokenSelective(WxAccessToken wxAccessToken) {
		wxAccessTokenMapper.updateWxAccessTokenSelective(wxAccessToken);
	}

	public void updateWxAccessTokenSelectiveBitch(List<WxAccessToken> wxAccessTokenList) {
		for (WxAccessToken wxAccessToken : wxAccessTokenList) {
			wxAccessTokenMapper.updateWxAccessTokenSelective(wxAccessToken);
		}
	}

	public void deleteWxAccessToken(Long id) {
		wxAccessTokenMapper.deleteWxAccessToken(id);
	}

	public void deleteWxAccessTokenBitch(List<Long> idList) {
		for (Long id : idList) {
			wxAccessTokenMapper.deleteWxAccessToken(id);
		}
	}

}