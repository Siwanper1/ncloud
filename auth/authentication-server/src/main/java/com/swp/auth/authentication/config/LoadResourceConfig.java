package com.swp.auth.authentication.config;

import com.swp.auth.authentication.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class LoadResourceConfig {

    @Autowired
    private ResourceService resourceService;

    @PostConstruct
    public void  loadResource(){
        resourceService.loadResource();
    }

}
