package com.example.backoffice.model.ValidEnum;

public enum Category {
    SPRING("SPRING"),
    REACT("REACT"),
    NODE("NODE");
    private final String category;
    Category (String category) {
        this.category = category;
    }
    public String getCategory() {
        return this.category;
    }
}
