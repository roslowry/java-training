package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.UploadFile;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/files")
public class FileController {

    private FileService fileService;
    private UserService userService;
    public FileController(FileService fileService, UserService userService) {

        this.fileService = fileService;
        this.userService = userService;
    }

    @PostMapping("/submitFile")
    public String uploadFile(Authentication authentication, Model model, @RequestParam("uploadFile") MultipartFile file) throws IOException {
        String username = authentication.getName();

        fileService.insertFile(new UploadFile(
                null,
                file.getOriginalFilename(),
                file.getContentType(),
                file.getSize(),
                userService.getUserByUserName(username).getUserid(),
                file.getBytes()
                ));

        return "redirect:/home";
    }


    @GetMapping("/download")
    public ResponseEntity<ByteArrayResource> getFile(@RequestParam(name="id") String id) {
        UploadFile file = fileService.getFileForDownload(Integer.parseInt(id));
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +  file.getFilename() + "\"" ).body(new ByteArrayResource(file.getFiledata()));
    }

    @GetMapping("/delete")
    public String deleteFile(Model model, @RequestParam(name = "id") String id) {
        Integer fileId = Integer.parseInt(id);
        fileService.deleteFile(fileId);
        return "redirect:/home";
    }


}
