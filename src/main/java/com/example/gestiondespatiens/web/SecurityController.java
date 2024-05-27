package com.example.gestiondespatiens.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @GetMapping("/noteAutorized")
    public String noteAutorized(){
        return "noteAutorized";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
