package com.solution.solutionapi.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.juli.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.solution.solutionapi.model.Stories;
import com.solution.solutionapi.repo.StoriesRepo;

@Component
public class DataManager {
    @Autowired
    private StoriesRepo repo;

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DataOperation dataOperation;

    @PostConstruct
    public void init() {
       task();
    }

    @Scheduled(cron = "0 0/12 * * * *")
    public void task(){
        long start = System.currentTimeMillis();
        logger.info("Started data pull ");
        List<Stories> stories =dataOperation.buildData();
        List<Stories> oldData = repo.getStories(Arrays.asList(1,2));
        if(oldData!=null)
            oldData =oldData.stream().map(a->{
                if(a.getLatest()==2){
                    a.setLatest(3);
                }else if(a.getLatest()==1){
                    a.setLatest(2);
                }
                return a;
            }).collect(Collectors.toList());
        repo.saveAll(oldData);
        repo.saveAll(stories);
        long end = System.currentTimeMillis();
        logger.info("data pull finished {} ",(end-start));
    }
	
}
