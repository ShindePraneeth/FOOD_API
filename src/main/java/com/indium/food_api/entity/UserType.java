package com.indium.food_api.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserType {
    DONOR, NGO;

    @JsonCreator
    public static UserType fromString(String value) {
        for (UserType type : UserType.values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown UserType: " + value);
    }

    @JsonValue
    public String toValue() {
        return name().toUpperCase();
    }
}
