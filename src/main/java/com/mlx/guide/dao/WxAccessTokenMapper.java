package com.mlx.guide.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.entity.WxAccessToken;

public interface WxAccessTokenMapper {

	public List<WxAccessToken> getWxAccessTokenList();

	public PageList<WxAccessToken> getWxAccessTokenList(PageBounds pageBounds);

	public List<WxAccessToken> getWxAccessTokenPageList(WxAccessToken wxAccessToken);

	public PageList<WxAccessToken> getWxAccessTokenPageList(WxAccessToken wxAccessToken, PageBounds pageBounds);

	public List<WxAccessToken> getWxAccessTokenPageListByMap(Map<String, Object> map);

	public PageList<WxAccessToken> getWxAccessTokenPageListByMap(Map<String, Object> map, PageBounds pageBounds);

	public WxAccessToken getWxAccessTokenByPrimaryKey(Long id);

	public void createWxAccessToken(WxAccessToken wxAccessToken);

	public void createWxAccessTokenSelective(WxAccessToken wxAccessToken);

	public void updateWxAccessToken(WxAccessToken wxAccessToken);

	public void updateWxAccessTokenSelective(WxAccessToken wxAccessToken);

	public void deleteWxAccessToken(Long id);
}