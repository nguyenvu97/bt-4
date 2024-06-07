package com.example.annotionjpa.annotation;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)

public @interface Id {
    String name() default "";
}
