package com.mungta.review.domain;

public enum Role {
    DRIVER("DRIVER"),
    CARPOOLER("CARPOOLER");

    private String role;
    Role(String role){
        this.role=role;
    }
}
