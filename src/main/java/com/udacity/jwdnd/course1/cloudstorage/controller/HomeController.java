package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.UploadFile;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.AuthenticationService;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;

import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/home")
public class HomeController {

    private FileService fileService;
    private UserService userService;

    public HomeController(FileService fileService, UserService userService) {

        this.fileService = fileService;
        this.userService = userService;
    }

    @GetMapping()
    public String goToHomePage(Model model){
        return "home";
    }

    @PostMapping("/submitFile")
    public String submitFile(Model model, @RequestParam("uploadFile") MultipartFile file, Authentication authentication) throws IOException {

        fileService.insertFile(new UploadFile(null, file.getOriginalFilename(), file.getContentType(), file.getSize(),userService.getUserByUserName(authentication.getName()).getUserid(), file.getBytes()));
        return "home";


    }


}
