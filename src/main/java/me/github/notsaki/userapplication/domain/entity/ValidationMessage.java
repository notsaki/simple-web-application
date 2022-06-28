package me.github.notsaki.userapplication.domain.entity;

public interface ValidationMessage {
    String notBlank = "should not be blank";
    String nameLength = "should be between 1-64 characters long";
    String datePast = "should be in the past";
    String addressLength = "should be between 1-128 characters long";
}
