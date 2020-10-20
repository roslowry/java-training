package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {

    private CredentialMapper credentialMapper;
    public CredentialService(CredentialMapper credentialMapper) {

        this.credentialMapper = credentialMapper;
    }

    public int insertCredentials(Credential credentials) {

        return credentialMapper.insertCredentials(credentials);
    }

    public List<Credential> getCredentialsByUser (Integer userid) {
        return credentialMapper.getCredentialsByUser(userid);
    }

    public void deleteCredentials(Integer credentialid){
        credentialMapper.deleteCredentials(credentialid);
    }

}
