package com.swp.auth.authentication.event;

import com.swp.auth.authentication.service.ResourceService;
import com.swp.organization.entity.po.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BusReceiver {
    @Autowired
    private ResourceService resourceService;

    public void handleMessage(Resource resource){
        log.info("receiver message : {}",resource);
        resourceService.save(resource);
    }

}
