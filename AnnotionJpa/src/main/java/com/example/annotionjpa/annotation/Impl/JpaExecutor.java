package com.example.annotionjpa.annotation.Impl;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
public interface JpaExecutor<T> {
    List<T> findAll();
    Optional<T> findById(Number id);

    List<T> search(String keyword);

    T  add(T t );
}
