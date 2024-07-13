package com.holoview.holoview.service;

public interface IEditable<T, ID, DTO> {
    T update(ID id, DTO dto);
}