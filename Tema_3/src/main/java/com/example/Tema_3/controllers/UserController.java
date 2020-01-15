package com.example.Tema_3.controllers;


import com.example.Tema_3.models.Resources;
import com.example.Tema_3.models.Rights;
import com.example.Tema_3.models.Role;
import com.example.Tema_3.models.Users;
import com.example.Tema_3.repository.*;
import com.example.Tema_3.service.UserService;
import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.*;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private ResourcesRepository resourcesRepository;
    @Autowired
    private RoleRepository roleRepository ;
    @Autowired
    private RightsRepository rightsRepository;
    private  static  Resources resStatic;

    @RequestMapping(path = {"/createResource", "/createResource/{id}/{id2}/{id3}"})
    public String createResource(Model model, @PathVariable("id") Optional<Long> idUser,@PathVariable("id2") Long idRole,@PathVariable("id3") Long idResursa) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users entity = usersRepository.findUsersByUsername(auth.getName());
        model.addAttribute("user", userService.getUserById(entity.getId()));
        if(entity.getRole().contains(roleRepository.findRoleById(idRole))!=true) {
            return createEntity(model,entity);
        }
        Resources resources1= resStatic;
        resources1.setText("Resursa a fost creata");
        Resources verifRes=resourcesRepository.findResourcesById(resStatic.getId());
        if(verifRes.getVersion()==resources1.getVersion()) {
            try {
                resourcesRepository.save(resources1);
                resourcesRepository.flush();
            } catch (StaleObjectStateException e) {
                System.out.println(e.getMessage());
                model.addAttribute("errorData", "Resursa nu a fost modificata");
                ModelAndView modele = new ModelAndView("modifyResource");
                modele.addObject("resources", new Resources());
                return createEntity(model,entity);
            }
        }
        else {
            model.addAttribute("errorData", "Resursa nu a fost modificata");
        }
        ModelAndView modele = new ModelAndView("modifyResource");
        modele.addObject("resources", new Resources());
        return createEntity(model,entity);
    }

    @RequestMapping(path = {"/deleteResource", "/deleteResource/{id}/{id2}/{id3}"})
    public String deleteResource(Model model, @PathVariable("id") Optional<Long> idUser,@PathVariable("id2") Long idRole,@PathVariable("id3") Long idResursa) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users entity = usersRepository.findUsersByUsername(auth.getName());
        model.addAttribute("user", userService.getUserById(entity.getId()));
        if(entity.getRole().contains(roleRepository.findRoleById(idRole))!=true) {
            return createEntity(model,entity);
        }
        Resources resources1= resStatic;
        resources1.setText(null);
        Resources verifRes=resourcesRepository.findResourcesById(resStatic.getId());
        if(verifRes.getVersion()==resources1.getVersion()) {
            try {
                resourcesRepository.save(resources1);
                resourcesRepository.flush();
            } catch (StaleObjectStateException e) {
                System.out.println(e.getMessage());
                model.addAttribute("errorData", "Resursa nu a fost modificata");
                ModelAndView modele = new ModelAndView("modifyResource");
                modele.addObject("resources", new Resources());
                return createEntity(model,entity);
            }
        }
        else {
            model.addAttribute("errorData", "Resursa nu a fost modificata");
        }
        ModelAndView modele = new ModelAndView("modifyResource");
        modele.addObject("resources", new Resources());
        return createEntity(model,entity);
    }

    @RequestMapping(value = "/modifyResource/{id}/{id2}/{id3}", method = RequestMethod.GET)
    public ModelAndView getdata(Model model,@PathVariable("id") Optional<Long> idUser,@PathVariable("id2") Long idRole,@PathVariable("id3") Long idResursa) throws IOException {
        ModelAndView model3 = new ModelAndView("modifyResource");
        model3.addObject("resources", new Resources());
        return model3;
    }

    @PostMapping(path = {"/modifyResource", "/modifyResource/{id}/{id2}/{id3}"})
    public String modifyResource(Model model,@ModelAttribute Resources resources, @PathVariable("id") Optional<Long> idUser,@PathVariable("id2") Long idRole,@PathVariable("id3") Long idResursa) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users entity= usersRepository.findUsersByUsername(auth.getName());
        model.addAttribute("user", userService.getUserById(entity.getId()));
        if(entity.getRole().contains(roleRepository.findRoleById(idRole))!=true) {
            return createEntity(model,entity);
        }
        Resources resources1= resStatic;
        if(resources.getText()!=null && resources.getText().isEmpty()!=true) {
            resources1.setText(resources.getText());
            Resources verifRes = resourcesRepository.findResourcesById(resStatic.getId());

            if (verifRes.getVersion() == resources1.getVersion()) {
                try {
                    resourcesRepository.save(resources1);
                    resourcesRepository.flush();
                } catch (StaleObjectStateException e) {
                    System.out.println(e.getMessage());
                    model.addAttribute("errorData", "Resursa nu a fost modificata");
                    ModelAndView modele = new ModelAndView("modifyResource");
                    modele.addObject("resources", new Resources());
                    return createEntity(model,entity);
                }
            }
            else {
                model.addAttribute("errorData", "Resursa nu a fost modificata");
            }
        }
        else {
            model.addAttribute("errorData", "Continutul resursei nu poate fi gol");
        }
        model.addAttribute("resources", new Resources());
        Role curentRole=roleRepository.findRoleById(idRole);
        model.addAttribute("roleId", curentRole.getId());
        for (Rights res:curentRole.getRights()
        ) {
            if(res.getName().equals("Modify"))
            {
                model.addAttribute("modify", res.getName());
            }
            else{
                model.addAttribute("modify", null);
            }
            if(res.getName().equals("Delete")) {
                model.addAttribute("delete", res.getName());
            }else{
                model.addAttribute("delete", null);
            }
            if(res.getName().equals("Write")) {
                model.addAttribute("write", res.getName());
            }else{
                model.addAttribute("write", null);
            }
            for (Resources resur : curentRole.getResources()
            ) {
                if(resur.getText().equals("") || resur.getText()==null)
                {
                    model.addAttribute("readRole",null);
                }
                else{
                    model.addAttribute("readRole", resur.getText());
                }
                model.addAttribute("resourceId", resur);
            }
        }
        ModelAndView modele = new ModelAndView("modifyResource");
        modele.addObject("resources", new Resources());
        return createEntity(model,entity);
    }

    @RequestMapping(path = {"/editResource", "/editResource/{id}/{id2}"})
    public String accesResourceByUserIdAndRoleId(Model model, @PathVariable("id") Optional<Long> idUser,@PathVariable("id2") Long idRole)
    {
        if (idUser.isPresent()) {
            Users entity = userService.getUserById(idUser.get());
            model.addAttribute("user", entity);
            if(entity.getRole().contains(roleRepository.findRoleById(idRole))!=true) {
                return createEntity(model,entity);
            }
            model.addAttribute("resources", new Resources());
            Role curentRole=roleRepository.findRoleById(idRole);
            model.addAttribute("roleId", curentRole.getId());
            for (Rights res:curentRole.getRights()
                 ) {
                if(res.getName().equals("Modify") )
                {
                    model.addAttribute("modify", res.getName());
                }
                if(res.getName().equals("Delete")) {
                    model.addAttribute("delete", res.getName());
                }
                if(res.getName().equals("Write")) {
                    model.addAttribute("write", res.getName());
                }
            }
            for (Resources resur : curentRole.getResources()
            ) {

                if( resur.getText()==null ||resur.getText().equals(""))
                {
                    model.addAttribute("readRole",null);
                }
                else{
                    model.addAttribute("readRole", resur.getText());
                }
                model.addAttribute("resourceId", resur);
                resStatic=resur;
                model.addAttribute("resourceModify",resur);
            }
            for (Role role: entity.getRole()
                 ) {
                if(role.getResources().contains(resStatic)==true){
                    for (Rights res:role.getRights()
                    ) {
                        if(res.getName().equals("Modify") )
                        {
                            model.addAttribute("modify", res.getName());
                        }
                        if(res.getName().equals("Delete")) {
                            model.addAttribute("delete", res.getName());
                        }
                        if(res.getName().equals("Write")) {
                            model.addAttribute("write", res.getName());
                        }
                    }
                }
            }
        } else {
            model.addAttribute("user", new Users());
        }
        roleRepository.flush();
        return "resourcePage";
    }

    public String createEntity(Model model,Users entity){
        model.addAttribute("roles", entity.getRole());
        List<String> right=new ArrayList<>();
        List<String> resourcess=new ArrayList<>();
        for (Role role : entity.getRole()
        ) {
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
            resourcess.add(resource);
            right.add(rights);
        }
        model.addAttribute("resources", resourcess);
        model.addAttribute("rights", right);
        return "userPage";
    }
}
