package com.unifiedweb360.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}