package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.UploadFile;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import org.springframework.security.core.parameters.P;
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

    public HomeController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping()
    public String goToHomePage(Model model){
        return "home";
    }

    @PostMapping("/submitFile")
    public String submitFile(Model model, @RequestParam("file") MultipartFile file) throws IOException {


        fileService.insertFile(new UploadFile(null, file.getOriginalFilename(), file.getContentType(), file.getSize(), file.getBytes()));
        return "home";


    }


}
