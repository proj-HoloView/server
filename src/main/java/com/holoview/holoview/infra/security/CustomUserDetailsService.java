package com.holoview.holoview.infra.security;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.holoview.holoview.model.entity.Admin;
import com.holoview.holoview.service.exception.NotFoundException;
import com.holoview.holoview.service.impl.AdminService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Admin adminFound = adminService.findByEmail(username);

            return new User(adminFound.getEmail(), adminFound.getPassword(), new ArrayList<>());
        } catch (NotFoundException e) {
            throw new UsernameNotFoundException("Admin not found");
        }
    }
}