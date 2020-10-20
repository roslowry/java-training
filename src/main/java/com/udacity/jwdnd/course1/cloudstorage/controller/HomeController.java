package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.UploadFile;
import com.udacity.jwdnd.course1.cloudstorage.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.security.auth.login.CredentialNotFoundException;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/home")
public class HomeController {

    private FileService fileService;
    private CredentialService credentialService;
    private UserService userService;
    private EncryptionService encryptionService;

    public HomeController(FileService fileService, CredentialService credentialService, UserService userService, EncryptionService encryptionService) {
        this.fileService = fileService;
        this.credentialService = credentialService;
        this.userService = userService;
        this.encryptionService = encryptionService;
    }

    @GetMapping()
    public String goToHomePage(Model model, Credential credential, Authentication authentication) {

        System.out.println("go to home page method...");

        List<UploadFile> allFiles = fileService.getUploadFiles();

        allFiles.forEach(f -> System.out.println("file name is: " + f.getFilename()));

        model.addAttribute("uploadedFilesInfo", getUserFilesForDisplay());
        model.addAttribute("uploadedCredentialsInfo", getUserCredentialsForDisplay(this.userService.getUserByUserName(authentication.getName()).getUserid()));
        return "home";

    }

    public List<Map<String, String>> getUserFilesForDisplay() {

        List<Map<String, String>> uploadedFilesInfo = new ArrayList<>();
        List<UploadFile> allFiles = fileService.getUploadFiles();

        Map<String, String> fileItem;

        for (UploadFile currentFile : allFiles) {
            fileItem = new HashMap<>();
            fileItem.put("name", currentFile.getFilename());
            fileItem.put("id", currentFile.getFileid().toString());
            uploadedFilesInfo.add(fileItem);
        }

        return uploadedFilesInfo;

    }

    public List<Map<String, String>> getUserCredentialsForDisplay(Integer userid) {

        List<Map<String, String>> uploadedCredentialsInfo = new ArrayList<>();
        List<Credential> credentials = credentialService.getCredentialsByUser(userid);
        Map<String, String> credentialItem;

        for (Credential credential: credentials) {
            credentialItem = new HashMap<>();
            credentialItem.put("url", credential.getUrl());
            credentialItem.put("username", credential.getUsername());
            credentialItem.put("password", encryptionService.decryptValue(credential.getPassword(), credential.getKey()));
            credentialItem.put("id", credential.getCredentialid().toString());
            uploadedCredentialsInfo.add(credentialItem);
        }

        return uploadedCredentialsInfo;

    }
}