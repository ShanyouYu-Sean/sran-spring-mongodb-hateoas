package com.hongshen.controller;

import com.hongshen.entity.nomalEntity.user.User;
import com.hongshen.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by a7289 on 2017/4/13 0013.
 */
@Controller
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = { "x-auth-token", "x-requested-with" })
@RequestMapping("/test")
public class TestController {
    @Autowired
    private UserRepository userRepository;
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<Authentication> user(Authentication auth ) {
        return new ResponseEntity<Authentication>(auth, HttpStatus.OK);
    }
}
