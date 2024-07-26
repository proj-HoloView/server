package com.holoview.holoview.service.impl;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.holoview.holoview.controller.dto.shop.InShopDTO;
import com.holoview.holoview.model.entity.Shop;
import com.holoview.holoview.model.repository.ShopRepository;
import com.holoview.holoview.service.exception.BadRequestException;
import com.holoview.holoview.service.exception.NotFoundException;

@ExtendWith(MockitoExtension.class)
public class ShopServiceTest {
    @InjectMocks
    ShopService service;

    @Mock
    ShopRepository repository;

    final String SHOP_NAME = "Loja um";

    Shop defaultShop = new Shop();
    InShopDTO shopDTO = new InShopDTO(SHOP_NAME);

    @BeforeEach
    public void setUp() {
        defaultShop.setActive(true);
        defaultShop.setName(SHOP_NAME);
    }

    @Test
    @DisplayName("Shop creation succesfully")
    void testCreateSuccess() {
        when(repository.save(defaultShop)).thenReturn(defaultShop);

        Shop newShop = service.create(shopDTO);

        assertEquals(newShop, defaultShop);
        verify(repository).findByName(SHOP_NAME);
        verify(repository).save(defaultShop);
    }

    @Test
    @DisplayName("Shop creation fail - Unavailable name")
    void testCreateUnavailableName() {
        when(repository.findByName(SHOP_NAME)).thenReturn(Optional.of(defaultShop));

        final BadRequestException e = assertThrows(BadRequestException.class, () -> {
            service.create(shopDTO);
        });

        assertNotNull(e);
        verify(repository).findByName(SHOP_NAME);
        verifyNoMoreInteractions(repository);
    }

    @Test
    @DisplayName("Shop delete succesfully")
    void testDeleteFound() {
        UUID shopId = UUID.randomUUID();

        when(repository.existsById(shopId)).thenReturn(Optional.of(defaultShop).isPresent());

        service.delete(shopId);
        verify(repository).deleteById(shopId);
    }

    @Test
    @DisplayName("Shop delete fail - Shop not Found")
    void testDeleteNotFound() {
        UUID shopId = UUID.randomUUID();

        when(repository.existsById(shopId)).thenReturn(Optional.of(defaultShop).isEmpty());

        final NotFoundException e = assertThrows(NotFoundException.class, () -> {
            service.delete(shopId);
        });

        assertNotNull(e);
        verify(repository).existsById(shopId);
        verifyNoMoreInteractions(repository);
    }

    @Test
    @DisplayName("Shop find all succesfully")
    void testFindAll() {
        Shop newShop = new Shop();

        newShop.setName("Loja dois");
        newShop.setActive(true);

        List<Shop> shops = Arrays.asList(defaultShop, newShop);

        when(repository.findAll()).thenReturn(shops);

        List<Shop> shopsFound = service.findAll();

        assertEquals(shopsFound, shops);
        verify(repository).findAll();
    }

    @Test
    @DisplayName("Shop find by id succesfully")
    void testFindByIdFound() {
        UUID shopId = UUID.randomUUID();

        when(repository.findById(shopId)).thenReturn(Optional.of(defaultShop));

        Shop shopFound = service.findById(shopId);

        assertEquals(shopFound, defaultShop);
        verify(repository).findById(shopId);
    }

    @Test
    @DisplayName("Shop find by id fail - Shop not found")
    void testFindByIdNotFound() {
        UUID shopId = UUID.randomUUID();

        when(repository.findById(shopId)).thenThrow(NotFoundException.class);

        final NotFoundException e = assertThrows(NotFoundException.class, () -> {
            service.findById(shopId);
        });

        assertNotNull(e);
        verify(repository).findById(shopId);
        verifyNoMoreInteractions(repository);
    }

    @Test
    @DisplayName("Shop update succesfully")
    void testUpdateFound() {
        UUID shopId = UUID.randomUUID();

        final String NEW_NAME = "Loja dois";

        InShopDTO newDTO = new InShopDTO(NEW_NAME);

        when(repository.findById(shopId)).thenReturn(Optional.of(defaultShop));
        when(repository.save(defaultShop)).thenReturn(defaultShop);

        Shop shopUpdated = service.update(shopId, newDTO);

        assertEquals(shopUpdated.getName(), NEW_NAME);
        verify(repository).findById(shopId);
        verify(repository).save(defaultShop);
    }

    @Test
    @DisplayName("Shop update fail - Shop not found")
    void testUpdateNotFound() {
        UUID shopId = UUID.randomUUID();

        final String NEW_NAME = "Loja dois";

        InShopDTO newDTO = new InShopDTO(NEW_NAME);

        when(repository.findById(shopId)).thenThrow(NotFoundException.class);

        final NotFoundException e = assertThrows(NotFoundException.class, () -> {
            service.update(shopId, newDTO);
        });

        assertNotNull(e);
        verify(repository).findById(shopId);
        verifyNoMoreInteractions(repository);
    }
}
