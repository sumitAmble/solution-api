package com.solution.solutionapi.utils;


import java.util.List;
import java.util.stream.Collectors;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.ehcache.event.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.solution.solutionapi.model.Stories;
import com.solution.solutionapi.repo.StoriesRepo;
import org.springframework.stereotype.Component;


public class CacheEventLogger implements CacheEventListener<Object, Object> {
	
	@Autowired
	private StoriesRepo repo;
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private DataOperation dataOperation;
	
    @Override
    public void onEvent(CacheEvent<? extends Object, ? extends Object> cacheEvent) {
    	org.ehcache.event.EventType t =cacheEvent.getType();
        log.info("******************the event occure **********************"+t.name());
    	
    	if(t==EventType.EXPIRED) {
			System.out.println("Event expired trying to refresh");
    		List<Stories> list =  repo.findAll();
    		repo.saveAll(updateTheData(list));
    		List<Stories> latestData =dataOperation.buildData();
    		repo.saveAll(latestData);
    	}
    }
    
    private List<Stories> updateTheData(List<Stories> list) {
    	return list.stream().map(a->{
    		if(a.getLatest()==1) {
    			a.setLatest(2);
    		}else if(a.getLatest()==2) {
    			a.setLatest(3);
    		}
    		return a;
    	}).collect(Collectors.toList());
    }
}