package com.holoview.holoview.service;

import java.util.UUID;

import com.holoview.holoview.controller.dto.admin.InAdminDTO;
import com.holoview.holoview.controller.dto.admin.InAdminShopDTO;
import com.holoview.holoview.model.entity.Admin;
import com.holoview.holoview.service.dto.AdminShopDTO;

public interface IAdminService extends
                IEditable<Admin, UUID, InAdminDTO>,
                IReadable<Admin, UUID>,
                IWritable<Admin, UUID, InAdminDTO> {
        Admin findByEmail(String email);
        Admin findByEmailOrUsername(String login);
        AdminShopDTO createAdminWithShop(InAdminShopDTO dto);
}