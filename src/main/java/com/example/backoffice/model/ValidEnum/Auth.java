package com.example.backoffice.model.ValidEnum;

public enum Auth {
    MANAGER("MANAGER"),
    STAFF("STAFF"),
    COMMON("COMMON");

    private final String auth;
    Auth(String auth) {
        this.auth = auth;
    }
    public String getAuth() {
        return this.auth;
    }
}
