package com.swp.auth.authorizationserver.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.swp.ncloud.common.core.entity.vo.Result;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonSerialize(using = CustomOauthExceptionSerialize.class)
public class CustomOAuth2Exception extends OAuth2Exception {

    private final Result result;

    public CustomOAuth2Exception(OAuth2Exception exception) {
        super(exception.getSummary(), exception);
        this.result = Result.fail(AuthErrorType.valueOf(exception.getOAuth2ErrorCode().toUpperCase()), exception);
    }
}
