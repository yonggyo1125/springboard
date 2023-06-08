package org.koreait.commons;

import org.springframework.http.HttpStatus;

public class AuthorizationException extends CommonException {
    public AuthorizationException() {
        super(bundleValidation.getString("UnAuthorization"), HttpStatus.UNAUTHORIZED);
    }

    public AuthorizationException(String code) {
        super(bundleValidation.getString(code), HttpStatus.UNAUTHORIZED);
    }
}
