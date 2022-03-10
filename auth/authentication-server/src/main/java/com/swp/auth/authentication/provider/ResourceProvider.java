package com.swp.auth.authentication.provider;

import com.swp.auth.authentication.entity.po.Resource;
import com.swp.ncloud.common.core.entity.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

@FeignClient(name = "organization", fallback = ResourceProviderFallback.class)
public interface ResourceProvider {

    @GetMapping("/resource/all")
    Result<Set<Resource>> resources();

    @GetMapping("/resource/user/{username}")
    Result<Set<Resource>> resources(@PathVariable("username") String username);

}
