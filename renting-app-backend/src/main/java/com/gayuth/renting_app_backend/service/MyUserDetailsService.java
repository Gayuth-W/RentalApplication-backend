package com.gayuth.renting_app_backend.service;

import com.gayuth.renting_app_backend.model.Seller;
import com.gayuth.renting_app_backend.model.SellerPrincipal;
import com.gayuth.renting_app_backend.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private SellerRepository sellerRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Seller user = sellerRepository.findByEmail(username);
        System.out.println(user);
        if (user == null) {
            System.out.println("User Not Found");
            throw new UsernameNotFoundException("user not found");
        }

        return new SellerPrincipal(user);
    }
}