package com.holoview.holoview.service;

import java.util.List;

public interface IReadable<T, ID> {
    List<T> findAll();

    T findById(ID id);
}