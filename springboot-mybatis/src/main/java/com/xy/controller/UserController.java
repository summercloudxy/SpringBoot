package com.xy.controller;

import javax.servlet.http.HttpServletRequest;

import com.xy.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @ClassName UserController
 * @author abel
 * @date 2016年11月10日
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    /***
     * api :localhost:8099/users?id=99
     *  http://localhost:8099/users?limit=2&page=2
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity list(HttpServletRequest request) {
       return new ResponseEntity<>(userService.getList(), HttpStatus.OK);

    }


}