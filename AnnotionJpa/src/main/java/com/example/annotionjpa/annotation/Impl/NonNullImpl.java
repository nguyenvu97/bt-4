package com.example.annotionjpa.annotation.Impl;

import com.example.annotionjpa.annotation.NonNull;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.Method;

@Aspect
public class NonNullImpl <T>{
    private Class<T> clazz;
    Method[] methods;

    public NonNullImpl(Class<T> clazz) {
        this.clazz = clazz;
        this.methods = methods;
    }

    @Bean
    public void checkAnnotation(Class<T> clazz){
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(NonNull.class)) {
                System.out.println("Method '" + method.getName() + "' is annotated with @NonNull");
                // Thực hiện xử lý khác tại đây nếu cần thiết
            }
        }
    }
}
