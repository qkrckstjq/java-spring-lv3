package com.example.backoffice.model.ValidEnum;

public enum Part {
    CURRICULUM("ROLE_CURRUCULUM"),
    DEVELOPMENT("ROLE_DEVELOPMENT"),
    MARKETING("ROLE_MARKETING");
    private final String part;
    Part(String part) {
        this.part = part;
    }
    public String getPart() {
        return this.part;
    }
}
