package com.example.Tema_3.controllers;

import com.example.Tema_3.controllers.dto.ResourcesDto;
import com.example.Tema_3.controllers.dto.convertor.ResoucesConverter;
import com.example.Tema_3.models.*;
import com.example.Tema_3.repository.*;
import com.example.Tema_3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.*;

@Controller
public class AdminController {

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

    @ModelAttribute("multiCheckboxAllValues")
    public String[] getMultiCheckboxAllValues() {
        String[] rights = new String[4];
        List<Rights> rightsList=rightsRepository.findAll();
        for (int i=0;i<rightsList.size();i++){
            rights[i]=rightsList.get(i).getName();
        }
        return rights;
    }
    @ModelAttribute("singleSelectAllValues")
    public String[] getSingleSelectAllValues() {

        List<Resources> rightsList=resourcesRepository.findAll();
        String[] rights = new String[rightsList.size()];
        for (int i=0;i<rightsList.size();i++){
            rights[i]=rightsList.get(i).getName();
        }
        return rights;
    }
    @GetMapping("/addNewResource")
    public String addNewResource(Model model) {
        model.addAttribute("newResources",new ResourcesDto());
        model.addAttribute("resources", new ResourcesDto());
        return "addResource";
    }
    @GetMapping("/addNewRole")
    public String addNewRole(Model model) {
        model.addAttribute( "command", new FormCommand());
        return "addNewRole";
    }
    @RequestMapping(value = "/addANewResource", method = RequestMethod.GET)
    public ModelAndView getdataResource(Model model) throws IOException {
        ModelAndView model3 = new ModelAndView("addANewResource");
        model3.addObject("resources", new ResourcesDto());
        return model3;
    }
    @RequestMapping(value = "/addANewRole", method = RequestMethod.GET)
    public String  getdataRole(Model model) throws IOException {
        model.addAttribute( "command", new FormCommand());
        return "addNewRole";
    }
    @PostMapping(path = {"/addANewRole"})
    public String addNewResource(Model model,@ModelAttribute("command") FormCommand command)
    {
        String[] strings=command.getMultiCheckboxSelectedValues();
        Set<Rights> rights=new HashSet<>();
        if(command.getRadioButtonSelectedValue()==null ||command.getMultiCheckboxSelectedValues()==null||command.getRadioButtonSelectedValue().isEmpty() ||command.getMultiCheckboxSelectedValues().length==0  )
        {
            model.addAttribute("errorData","Trebuie sa selectezi un Drept si o Resursa");
            model.addAttribute( "command", new FormCommand());
            return "addNewRole";
        }
        for (String st: strings
             ) {
            rights.add(rightsRepository.findRightsByName(st));
        }
        Set<Resources> resources=new HashSet<>();
        resources.add(resourcesRepository.findResourcesByName(command.getRadioButtonSelectedValue()));
        List<Role> roleList=roleRepository.findAll();
        int nr=roleList.size()+1;
        Role newRole=new Role((long) nr);
        newRole.setRights(rights);
        newRole.setResources(resources);
        roleRepository.save(newRole);
        List<Users> list = userService.findAll("ROLE_USER");
        model.addAttribute("users", list);
        return "adminPage";
    }
    @PostMapping(path = {"/addANewResource"})
    public String addNewResource(Model model,@ModelAttribute ResourcesDto newResources)
    {
        List<Resources> resourcesList=resourcesRepository.findAll();
        int nr=resourcesList.size()+1;
        newResources.setName("Resource "+nr);
        resourcesRepository.save(ResoucesConverter.of(newResources));
        List<Users> list = userService.findAll("ROLE_USER");
        model.addAttribute("users", list);
        return "adminPage";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editUserRoleById(Model model, @PathVariable("id") Optional<Long> id)
    {
        List<String> right=new ArrayList<>();
        List<String> resources=new ArrayList<>();
        if (id.isPresent()) {
            Users entity = userService.getUserById(id.get());
            model.addAttribute("user", entity);
            createDataUser(model,entity);
//            model.addAttribute("roles", entity.getRole());
//            for (Role role:entity.getRole()
//            ) {
//                String rights="";
//                String resource="";
//                for (Rights rig: role.getRights()
//                ) {
//                    if(rights!="") {
//                        rights = rights + " + " + rig.getName();
//                    }else{
//                        rights=rig.getName();
//                    }
//                }
//                for (Resources res:role.getResources()
//                ) {
//                    resource=res.getName();
//                }
//                resources.add(resource);
//                right.add(rights);
//            }
//            model.addAttribute("resources", resources);
//            model.addAttribute("rights", right);
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
        //List<String> right=new ArrayList<>();
        //List<String> resources=new ArrayList<>();
        if (id.isPresent()) {
            Long idd=id.get();
            Users entity = userService.getUserById(id.get());
            Role roleDelete=roleRepository.findRoleById(id2);
            entity.getRole().add(roleDelete);
            usersRepository.save(entity);
            createDataUser(model,entity);
//            model.addAttribute("user", entity);
//            model.addAttribute("roles", entity.getRole());
//            for (Role role:entity.getRole()
//            ) {
//                String rights="";
//                String resource="";
//                for (Rights rig: role.getRights()
//                ) {
//                    if(rights!="") {
//                        rights = rights + " + " + rig.getName();
//                    }else{
//                        rights=rig.getName();
//                    }
//                }
//                for (Resources res:role.getResources()
//                ) {
//                    resource=res.getName();
//                }
//                resources.add(resource);
//                right.add(rights);
//            }
//            model.addAttribute("resources", resources);
//            model.addAttribute("rights", right);
        } else {
            model.addAttribute("user", new Users());
        }
        return "edit-user";
    }

    @RequestMapping(path = "/delete/{id}/{id2}")
    public String deleteRoleUserById(Model model, @PathVariable("id") Optional<Long> id,@PathVariable("id2") Long id2)
    {
        //List<String> right=new ArrayList<>();
       // List<String> resources=new ArrayList<>();
        if (id.isPresent()) {
            Long idd=id.get();
            Users entity = userService.getUserById(id.get());
            Role roleDelete=roleRepository.findRoleById(id2);
            entity.getRole().remove(roleDelete);
            usersRepository.save(entity);
            createDataUser(model,entity);
//            model.addAttribute("user", entity);
//            model.addAttribute("roles", entity.getRole());
//            for (Role role:entity.getRole()
//            ) {
//                String rights="";
//                String resource="";
//                for (Rights rig: role.getRights()
//                ) {
//                    if(rights!="") {
//                        rights = rights + " + " + rig.getName();
//                    }else{
//                        rights=rig.getName();
//                    }
//                }
//                for (Resources res:role.getResources()
//                ) {
//                    resource=res.getName();
//                }
//                resources.add(resource);
//                right.add(rights);
//            }
//            model.addAttribute("resources", resources);
//            model.addAttribute("rights", right);
        } else {
            model.addAttribute("user", new Users());
        }
        return "edit-user";
    }
    public void createDataUser(Model model,Users entity){
        List<String> right=new ArrayList<>();
        List<String> resources=new ArrayList<>();
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
    }
}
