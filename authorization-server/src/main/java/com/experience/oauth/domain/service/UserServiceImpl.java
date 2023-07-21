package com.experience.oauth.domain.service;

import com.experience.oauth.domain.dao.UserDao;
import com.experience.oauth.domain.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = this.userDao.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("USERNAME NOT FOUND"));
        return new User(userEntity.getUsername(), userEntity.getPassword(), true, true, true, true,
                List.of(new SimpleGrantedAuthority("USER")));
    }
}
