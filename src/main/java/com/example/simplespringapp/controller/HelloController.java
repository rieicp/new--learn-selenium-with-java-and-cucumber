package com.example.simplespringapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping(method = RequestMethod.GET, path="/hello")
    public String hello () {
        return "hali halo";
    }

    @RequestMapping(method = RequestMethod.GET, path="/home")
    public String home () {
        return "home";
    }

}
