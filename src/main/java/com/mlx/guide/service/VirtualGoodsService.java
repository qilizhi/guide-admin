package com.mlx.guide.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.dao.VirtualGoodsMapper;
import com.mlx.guide.entity.VirtualGoods;

@Service
@Transactional
public class VirtualGoodsService {

	@Autowired
	private VirtualGoodsMapper virtualGoodsMapper;

	public int deleteByPrimaryKey(Long id) {

		return virtualGoodsMapper.deleteByPrimaryKey(id);
	}

	public int insert(VirtualGoods record) {

		return virtualGoodsMapper.insert(record);
	}

	public int insertSelective(VirtualGoods record) {

		return virtualGoodsMapper.insertSelective(record);
	}

	public VirtualGoods selectByPrimaryKey(Long id) {

		return virtualGoodsMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(VirtualGoods record) {

		return virtualGoodsMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(VirtualGoods record) {

		return virtualGoodsMapper.updateByPrimaryKey(record);
	}

	public PageList<VirtualGoods> listByExample(VirtualGoods vGoods, PageBounds pageBounds) {
		return virtualGoodsMapper.listByExample(vGoods, pageBounds);
	}

	public int batDelByflag(String[] idsArray) {

		return virtualGoodsMapper.batDelByflag(idsArray);
	};

}
