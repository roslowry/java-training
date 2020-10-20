package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.UploadFile;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.service.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/credentials")
public class CredentialController {

    private FileService fileService;
    private UserService userService;
    private EncryptionService encryptionService;
    private CredentialService credentialService;

    public CredentialController(FileService fileService, UserService userService, EncryptionService encryptionService, CredentialService credentialService) {
//
        this.fileService = fileService;
        this.userService = userService;
        this.encryptionService = encryptionService;
        this.credentialService = credentialService;
    }

    @PostMapping("/addCredentials")
    public String addCredentials(Model model, Credential credentials, Authentication authentication) {

        System.out.println(credentials.getUrl());

        User currentUser = userService.getUserByUserName(authentication.getName());
        String salt = encryptionService.createSalt();
        credentials.setUserid(currentUser.getUserid());
        credentials.setPassword(encryptionService.encryptValue(credentials.getPassword(), salt));
        credentials.setKey(salt);
        credentialService.insertCredentials(credentials);

        return "redirect:/home";
    }

    @GetMapping("/delete")
    public String deleteCredentials(@RequestParam(name = "credentialid") String credentialid) {
        credentialService.deleteCredentials(Integer.parseInt(credentialid));
        return "redirect:/home";

    }

    @PostMapping("/edit") // need to add body logic here...
    public String editCredentials() {
        return "redirect:/home";
    }// add request param or body etc.

//    @P
//
//    @PostMapping("/edit")
//    public String editCredentials(@RequestParam ){
//        return "redirect:/home";
//
//
//    }





}
