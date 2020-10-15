package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.UploadFile;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

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

    public List<UploadFile> getUploadFiles() {
        return fileMapper.getAllFiles();
    }

    public void deleteFile(Integer id) {
        fileMapper.deleteFile(id);
        System.out.println("i deleted a file");
    }

    public UploadFile getFileForDownload(Integer id) {
        return fileMapper.downloadFile((id));
    }

}
