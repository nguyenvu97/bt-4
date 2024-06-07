package com.example.annotionjpa.annotation;

public enum GenerationType {
    TABLE,
    SEQUENCE,
    IDENTITY,
    UUID,
    SERIAL,
    AUTO;

    private GenerationType() {
    }

}
