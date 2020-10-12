package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.UploadFile;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class FileService {

    private FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public int insertFile(UploadFile file){
        int uploadInt = fileMapper.insertFile(file);
        System.out.println("I uploaded the file successfully; the int is: " + uploadInt);
        return uploadInt;
    }

}
