package com.example.Tema_3.controllers;

import com.example.Tema_3.models.*;
import com.example.Tema_3.repository.*;
import com.example.Tema_3.service.ResourcesRightsService;
import com.example.Tema_3.service.UserService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    public String user() {
        return "index";
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

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editUserRoleById(Model model, @PathVariable("id") Optional<Long> id)
    {
        List<String> right=new ArrayList<>();
        List<String> resources=new ArrayList<>();
        List<Long> roleId=new ArrayList<>();
        if (id.isPresent()) {
            Users entity = userService.getUserById(id.get());
            System.out.println(entity);
            model.addAttribute("user", entity);
            Set<Role> roleSet=new HashSet<>();
            model.addAttribute("roles", entity.getRole());
            for (Role role:entity.getRole()
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
        } else {
            model.addAttribute("user", new Users());
        }
        return "edit-user";
    }

    @RequestMapping(path = {"/addRole", "/addRole/{id}"})
    public String addRoleById(Model model, @PathVariable("id") Optional<Long> id)
    {
        List<String> right=new ArrayList<>();
        List<String> resources=new ArrayList<>();
        if (id.isPresent()) {
            Users entity = userService.getUserById(id.get());
            System.out.println(entity);
            model.addAttribute("user", entity);
            List<Role> roles=roleRepository.findAll();
            Set<Role> roleSet=new HashSet<>();
            for (Role role:roles
            ) {
                entity.getRole().contains(role);
                if( entity.getRole().contains(role)!=true) {
                    roleSet.add(role);
                    String rights = "";
                    String resource = "";
                    for (Rights rig : role.getRights()
                    ) {
                        if (rights != "") {
                            rights = rights + " + " + rig.getName();
                        } else {
                            rights = rig.getName();
                        }
                    }
                    for (Resources res : role.getResources()
                    ) {
                        resource = res.getName();
                    }
                    resources.add(resource);
                    right.add(rights);
                }
            }
            model.addAttribute("roles", roleSet);
            model.addAttribute("resources", resources);
            model.addAttribute("rights", right);
        } else {
            model.addAttribute("user", new Users());
        }
        return "add-role-to-user";
    }

    @RequestMapping(path ={"/add", "/add/{id}/{id2}"} )
    public String addRoleToUserById(Model model, @PathVariable("id") Optional<Long> id,@PathVariable("id2") Long id2)
    {
        List<String> right=new ArrayList<>();
        List<String> resources=new ArrayList<>();
        if (id.isPresent()) {
            Long idd=id.get();
            Users entity = userService.getUserById(id.get());
            Role roleDelete=roleRepository.findRoleById(id2);
            entity.getRole().add(roleDelete);
            usersRepository.save(entity);
            model.addAttribute("user", entity);
            model.addAttribute("roles", entity.getRole());

            for (Role role:entity.getRole()
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
        } else {
            model.addAttribute("user", new Users());
        }
        return "edit-user";
    }

    @RequestMapping(path = "/delete/{id}/{id2}")
    public String deleteRoleUserById(Model model, @PathVariable("id") Optional<Long> id,@PathVariable("id2") Long id2)
    {
        List<String> right=new ArrayList<>();

        List<String> resources=new ArrayList<>();
        if (id.isPresent()) {
            Long idd=id.get();
            Users entity = userService.getUserById(id.get());
            Role roleDelete=roleRepository.findRoleById(id2);
            entity.getRole().remove(roleDelete);
            usersRepository.save(entity);
            model.addAttribute("user", entity);
            model.addAttribute("roles", entity.getRole());
            for (Role role:entity.getRole()
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
        } else {
            model.addAttribute("user", new Users());
        }
        return "edit-user";
    }
    
}
