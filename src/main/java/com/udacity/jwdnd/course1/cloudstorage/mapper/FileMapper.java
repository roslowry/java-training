package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.UploadFile;
import org.apache.ibatis.annotations.*;
import org.springframework.core.io.Resource;

import java.sql.Blob;
import java.util.List;

@Mapper
public interface FileMapper {

    @Insert("INSERT INTO FILES (fileid, filename, contenttype, filesize, userid, filedata) VALUES (#{fileid}, #{filename},#{contenttype},#{filesize},#{userid},#{filedata})")
    @Options(useGeneratedKeys = true, keyProperty = "userid")
    public int insertFile(UploadFile file);

    @Select("SELECT * FROM FILES")
    public List<UploadFile> getAllFiles();

    @Delete("DELETE FROM FILES where fileid = #{id}")
    public void deleteFile(Integer id);

    @Select("SELECT * FROM FILES where fileid = #{id}")
    public UploadFile downloadFile(Integer id);


}