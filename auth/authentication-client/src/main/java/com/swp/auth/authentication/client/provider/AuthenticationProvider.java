package com.swp.auth.authentication.client.provider;

import com.swp.ncloud.common.core.entity.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "authentication-client", fallback = AuthenticationProvider.AuthenticationProviderFallback.class)
public interface AuthenticationProvider {

    @PostMapping("/auth/permission")
    Result auth(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization, @RequestParam("url") String url,@RequestParam("method") String method);

     class AuthenticationProviderFallback implements AuthenticationProvider {
        @Override
        public Result auth(String authorization, String url, String method) {
            return Result.fail();
        }
    }

}
