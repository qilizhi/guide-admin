package com.mlx.guide.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.dao.PlatformMsgMapper;
import com.mlx.guide.entity.PlatformMsg;
import com.mlx.guide.entity.UserInfo;

@Service
@Transactional
public class PlatformMsgService {

	@Autowired
	private PlatformMsgMapper platformMsgMapper;
	
    public int deleteByPrimaryKey(Long id){
    	return platformMsgMapper.deleteByPrimaryKey(id);
    }

    public int insert(PlatformMsg record){
    	return platformMsgMapper.insert(record);
    }

    public int insertSelective(PlatformMsg record){
    	return platformMsgMapper.insertSelective(record);
    }

    public PlatformMsg selectByPrimaryKey(Long id){
    	return platformMsgMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(PlatformMsg record){
    	return platformMsgMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(PlatformMsg record){
    	return platformMsgMapper.updateByPrimaryKey(record);
    }
    
    public List<PlatformMsg> getPlatformMsgList(){
    	return platformMsgMapper.getPlatformMsgList();
    }

    
	public PageList<PlatformMsg> getPageList(PlatformMsg platformMsg,
			PageBounds pageBounds) {
		return platformMsgMapper.getPageList(platformMsg,pageBounds);
		
	}

	
	public PlatformMsg getPlatformMsgById(Long id) {
		return platformMsgMapper.getPlatformMsgById(id);
	}
}
