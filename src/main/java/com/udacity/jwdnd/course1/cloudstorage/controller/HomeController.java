package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.UploadFile;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.util.List;


@Controller
@RequestMapping("/home")
public class HomeController {

    private FileService fileService;

    public HomeController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping()
    public String goToHomePage(Model model){

        List<UploadFile> allFiles = fileService.getUploadFiles();

        allFiles.forEach(f -> System.out.println("file name is: " + f.getFilename()));

        model.addAttribute("uploadedFiles", fileService.getUploadFiles());
        return "home";
    }

}
