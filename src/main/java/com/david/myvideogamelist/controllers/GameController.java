package com.david.myvideogamelist.controllers;

import com.david.myvideogamelist.models.Game;
import com.david.myvideogamelist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @GetMapping("/games")
    public String games(Model model) {

        model.addAttribute("gameList", gameRepository.findAll());
        return "games/index";
    }

    @GetMapping("/games/new")
    public String newGame(@ModelAttribute("game") Game game) {
        return "/games/form";
    }

    @PostMapping("/games/save")
    public String addGame(@ModelAttribute("game") Game game) {
        gameRepository.save(game);
        return "redirect:/games";
    }
}
