package com.swp.auth.authentication.provider;

import com.swp.auth.authentication.entity.po.Resource;
import com.swp.ncloud.common.core.entity.vo.Result;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

@Slf4j
public class ResourceProviderFallback implements ResourceProvider{
    @Override
    public Result<Set<Resource>> resources() {
        log.info("加载所有资源错误");
        return Result.fail();
    }

    @Override
    public Result<Set<Resource>> resources(String username) {
        log.info("此用户加载资源错误");
        return Result.fail(new HashSet<Resource>());
    }
}
