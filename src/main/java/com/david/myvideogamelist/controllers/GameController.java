package com.david.myvideogamelist.controllers;

import com.david.myvideogamelist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @GetMapping("/games")
    public String games(Model model) {

        model.addAttribute("gameList", gameRepository.findAll());
        return "games/index";
    }
}
