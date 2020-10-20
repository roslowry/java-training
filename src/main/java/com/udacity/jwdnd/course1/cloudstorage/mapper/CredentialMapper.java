package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CredentialMapper {

    @Insert("INSERT INTO CREDENTIALS (credentialid, url, username, key, password, userid) VALUES (#{credentialid},#{url},#{username},#{key},#{password},#{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialid")
    public int insertCredentials(Credential credentials);

    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userid}")
    public List<Credential> getCredentialsByUser (Integer userid);

    @Select("DELETE * FROM CREDENTIALS where credentialid = #{credentialid}")
    public void deleteCredentials(Integer credentialid);
}
