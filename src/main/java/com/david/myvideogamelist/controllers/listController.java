package com.david.myvideogamelist.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class listController {

    @RequestMapping("/games")
    public String getData(Model model) {

        model.addAttribute("message", "Hello World!");
        return "games/index";
    }
}
