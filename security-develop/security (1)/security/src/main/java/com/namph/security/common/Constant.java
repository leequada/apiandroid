package com.namph.security.common;

public interface Constant {
    String MESSAGE_NOT_BLANK="not.null";
    interface Status{
        String SUCCESS="1";
        String ERROR="2";
    }
    interface ERROR_CODE{
        String SUCCESS="1";
        String VALIDATE_FAIL="2";
    }
    interface ROLE{
        String USER="ROLE_USER";
        String ADMIN="ROLE_ADMIN";
    }
}
