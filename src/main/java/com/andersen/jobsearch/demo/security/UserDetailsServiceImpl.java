package com.andersen.jobsearch.demo.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.andersen.jobsearch.demo.entity.Role;
import com.andersen.jobsearch.demo.entity.User;
import com.andersen.jobsearch.demo.exception.UserIsBannedException;
import com.andersen.jobsearch.demo.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		User user = userService.findByUsername(username);
		
		if (user != null) 
		{
			if(user.isBanned())
			{
				log.info("User with username: " + username + " is banned.");
				throw new UsernameNotFoundException("User with username: " + username + " is banned.");
			}
			
            List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
            return buildUserForAuthentication(user, authorities);
        }
		else
			log.info("User with username: " + username + " does not exist.");
            throw new UsernameNotFoundException("User with username: " + username + " does not exist.");
	}
	
	private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) 
	{
        Set<GrantedAuthority> roles = new HashSet<>();
        
        userRoles.forEach((role) -> 
        { 
        	roles.add(new SimpleGrantedAuthority(role.getRole()));}
        );
        
        return new ArrayList<GrantedAuthority>(roles);
    }

    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) 
    {
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
