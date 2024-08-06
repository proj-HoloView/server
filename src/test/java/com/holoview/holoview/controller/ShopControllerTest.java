package com.holoview.holoview.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.holoview.holoview.controller.dto.shop.InShopDTO;
import com.holoview.holoview.controller.dto.shop.OutShopDTO;
import com.holoview.holoview.model.entity.Shop;
import com.holoview.holoview.service.impl.ShopService;

@ExtendWith(MockitoExtension.class)
public class ShopControllerTest {
    @InjectMocks
    ShopController controller;

    @Mock
    ShopService service;

    Shop defaultShop = new Shop();
    UUID shopId = UUID.randomUUID();
    final String SHOP_NAME = "Loja teste";
    InShopDTO shopDTO = new InShopDTO(SHOP_NAME);

    @BeforeEach
    void setUp() {
        defaultShop.setName(SHOP_NAME);
        defaultShop.setId(shopId);
    }

    @Test
    void testPostShopSuccess() throws Exception {
        when(service.create(shopDTO)).thenReturn(defaultShop);

        ResponseEntity<OutShopDTO> response = controller.postShop(shopDTO);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals(response.getBody().id(), shopId);
        verify(service).create(shopDTO);
    }

    @Test
    void testGetById() throws Exception {
        when(service.findById(shopId)).thenReturn(defaultShop);

        ResponseEntity<OutShopDTO> response = controller.getById(shopId);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals(response.getBody().id(), shopId);
        verify(service).findById(shopId);
    }

    @Test
    void testGetAll() {
        when(service.findAll()).thenReturn(Arrays.asList(defaultShop));

        ResponseEntity<List<OutShopDTO>> response = controller.getAll();

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertFalse(response.getBody().isEmpty());
        verify(service).findAll();
    }

    @Test
    void testDeleteShop() {
        ResponseEntity<?> response = controller.deleteShop(shopId);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        verify(service).delete(shopId);
    }

    @Test
    void testUpdateShop() {
        InShopDTO newShopDTO = new InShopDTO("Loja atualizada");
        Shop updatedShop = new Shop();
        updatedShop.setName(newShopDTO.name());

        when(service.update(shopId, newShopDTO)).thenReturn(updatedShop);

        ResponseEntity<OutShopDTO> response = controller.updateShop(shopId, newShopDTO);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals(response.getBody().name(), newShopDTO.name());
    }
}
