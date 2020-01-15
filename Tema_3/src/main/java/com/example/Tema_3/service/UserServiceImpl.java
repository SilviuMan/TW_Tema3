package com.example.Tema_3.service;

import com.example.Tema_3.controllers.dto.UserRegistrationDto;
import com.example.Tema_3.models.Roles;
import com.example.Tema_3.models.Users;
import com.example.Tema_3.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Users findUsersByUsername(String username){
        return userRepository.findUsersByUsername(username);
    }

    public Users getUserById(Long id)
    {
        Optional<Users> employee = Optional.ofNullable(userRepository.findUsersById(id));

        if(employee.isPresent()) {
            return employee.get();
        }
        else
            return new Users();

    }
    public  List<Users> findAll(String roles)
    {
        List<Users> result = (List<Users>) userRepository.findAll();
        List<Users> usersGood=new ArrayList<Users>();
        for (Users s:result
        ) {
            if (s.getUsername().contains("admin")!=true)
            {

                    usersGood.add(s);
            }
        }
        if(usersGood.size() > 0) {
            return usersGood;
        } else {
            return new ArrayList<Users>();
        }
    }

    public Users save(UserRegistrationDto registration){
        Users user = new Users();
        user.setUsername(registration.getUsername());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        user.setRoles(Arrays.asList(new Roles("ROLE_USER")));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findUsersByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Roles> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
