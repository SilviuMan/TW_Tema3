package com.example.Tema_3.controllers;

import com.example.Tema_3.models.*;
import com.example.Tema_3.repository.*;
import com.example.Tema_3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Controller
public class MainController {
    @Autowired
    private UserService userService;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private ResourcesRightsRepository resourcesRightsService;
    @Autowired
    private ResourceRightsRoleRepository resourceRightsRoleRepository;
    @Autowired
    private ResourcesRepository resourcesRepository;
    @Autowired
    private RoleRepository roleRepository ;
    @Autowired
    private RightsRepository rightsRepository;


    @GetMapping("/")
    public String root() {
        return "login";
    }

    @GetMapping("/user")
    public String user(Model model) {
        Authentication auth =SecurityContextHolder.getContext().getAuthentication();
        Users user= usersRepository.findUsersByUsername(auth.getName());
        List<String> right=new ArrayList<>();
        List<String> resources=new ArrayList<>();
        model.addAttribute("user", user);
        model.addAttribute("roles", user.getRole());
        for (Role role:user.getRole()
        ) {
            String rights="";
            String resource="";
            for (Rights rig: role.getRights()
            ) {
                if(rights!="") {
                    rights = rights + " + " + rig.getName();
                }else{
                    rights=rig.getName();
                }
            }
            for (Resources res:role.getResources()
            ) {
                resource=res.getName();
            }
            resources.add(resource);
            right.add(rights);
        }
        model.addAttribute("resources", resources);
        model.addAttribute("rights", right);
        return "userPage";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        List<Users> list = userService.findAll("ROLE_USER");
        model.addAttribute("users", list);
        return "adminPage";
    }

    
}
