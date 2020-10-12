package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;


@Service
public class UserService {

    private UserMapper userMapper;
    private HashService hashService;
    private EncryptionService encryptionService;

    public UserService(UserMapper userMapper, HashService hashService, EncryptionService encryptionService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
        this.encryptionService = encryptionService;
    }

    public boolean isUsernameAvailable(String userName) {
        return userMapper.findUserByUserName(userName) == null;
    }

    public int createUser(User user) {
        String saltToEncodePasswordWith = createSalt();
        String saltedPassword = hashService.getHashedValue(user.getPassword(), saltToEncodePasswordWith);
//        String encryptedPassword = encryptPassword(user.getPassword())
//        user.setSalt(saltToEncodePasswordWith);

        return userMapper.insert(new User(null, user.getFirstname(), user.getLastname(), user.getUsername(), saltedPassword, saltToEncodePasswordWith));
//        return userMapper.insert(user.getUsername(), saltToEncodePasswordWith, user.getPassword(), user.getFirstname(), user.getLastname());
    }

    public String createSalt () {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        return encodedSalt;
    }

    public User getUserByUserName(String username) {
        return userMapper.findUserByUserName(username);
    }

//    public String encryptPassword(String password, String encryptionKey) {
//        encryptionService.encryptValue(password, encryptionKey);
//
//    }
//
//    public String decryptPassword(String encryptedPassword, String encryptionKey) {
//        encryptionService.decryptValue(encryptedPassword, encryptionKey);
//
//    }

}
