package com.hush.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/page")
public class ModelController {
    @GetMapping("/index")
    public String init(){       
        return "index";
    }
    
    @GetMapping("/404")
    public String erro(){       
        return "404";
    }
    
    @GetMapping("/login")
    public String Login(){       
        return "login";
    }

    @GetMapping("/register")
    public String register(){       
        return "register";
    }
}