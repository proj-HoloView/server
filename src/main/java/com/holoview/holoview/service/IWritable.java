package com.holoview.holoview.service;

public interface IWritable<T, ID, DTO> {
    T create(DTO dto);

    void delete(ID id);
}