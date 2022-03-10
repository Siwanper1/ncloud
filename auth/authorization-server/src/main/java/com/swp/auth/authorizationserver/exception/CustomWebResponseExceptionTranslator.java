package com.swp.auth.authorizationserver.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

public class CustomWebResponseExceptionTranslator implements WebResponseExceptionTranslator<OAuth2Exception> {
    @Override
    public ResponseEntity translate(Exception e) throws Exception {
        OAuth2Exception exception = (OAuth2Exception)e;
        return ResponseEntity.status(exception.getHttpErrorCode()).body(new CustomOAuth2Exception(exception));
    }
}
