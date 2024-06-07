package com.example.annotionjpa.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Entity {
    String tableName()  default "";
}
