/**
 * 
 */
package com.mlx.guide.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.dao.EmGroupMapper;
import com.mlx.guide.entity.EmGroup;

/**
 * @author QiQi-04-PC
 *
 */
@Service
public class EmGroupService implements EmGroupMapper {

	@Autowired
	private EmGroupMapper emGroupMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return emGroupMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(EmGroup record) {
		return emGroupMapper.insert(record);
	}

	@Override
	public int insertSelective(EmGroup record) {
		return emGroupMapper.insertSelective(record);
	}

	@Override
	public EmGroup selectByPrimaryKey(Long id) {
		return emGroupMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(EmGroup record) {
		return emGroupMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(EmGroup record) {
		return emGroupMapper.updateByPrimaryKey(record);
	}
	@Override
	public PageList<EmGroup> listBySelective(EmGroup record, PageBounds pageBounds) {
		return emGroupMapper.listBySelective( record,pageBounds);
	}
	@Override
	public List<EmGroup> listBySelective(EmGroup record) {
		return emGroupMapper.listBySelective( record);
	}

	public void batDeleteByPrimaryKey(String[] ids) {
		emGroupMapper.batDeleteByPrimaryKey(ids);
	}

}
