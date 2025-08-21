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
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Seller seller = sellerRepository.findByEmail(email);
        if (seller == null) {
            System.out.println("User Not Foundsdsd");
            throw new UsernameNotFoundException("user not found1212");
        }

        return new SellerPrincipal(seller);
    }
}