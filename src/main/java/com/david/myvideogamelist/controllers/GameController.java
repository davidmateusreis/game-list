package com.david.myvideogamelist.controllers;

import com.david.myvideogamelist.models.Developer;
import com.david.myvideogamelist.models.Game;
import com.david.myvideogamelist.repositories.DeveloperRepository;
import com.david.myvideogamelist.repositories.GameRepository;
import com.david.myvideogamelist.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class GameController {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private DeveloperRepository developerRepository;

    public GameController(GameRepository gameRepository, DeveloperRepository developerRepository) {
        this.gameRepository = gameRepository;
        this.developerRepository = developerRepository;
    }

    @GetMapping("/games")
    public String games(Model model) {

        model.addAttribute("gameList", gameRepository.findAll());
        return "games/index";
    }

    @GetMapping("/games/new")
    public String newGame(Model model) {
        model.addAttribute("game", new Game(""));
        model.addAttribute("developer", developerRepository.findAll());
        return "/games/form";
    }

    @PostMapping("/games/save")
    public String addGame(@Valid @ModelAttribute("game") Game game, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("developer", developerRepository.findAll());
            return "games/form";
        }
        gameRepository.save(game);
        return "redirect:/games";
    }

    @GetMapping("/games/{id}")
    public String updateGame(@PathVariable("id") long id, Model model) {
        Optional<Game> gameOptional = gameRepository.findById(id); //evitar problemas com valores nulos
        if (gameOptional.isEmpty()) {
            throw new IllegalArgumentException("Invalid Game");
        }
        model.addAttribute("game", gameOptional.get());
        model.addAttribute("developer", developerRepository.findAll());
        return "games/form";

    }

    @GetMapping("/games/delete/{id}") //poderia ser DeleteMapping, mas HTML só suporta GET e POST
    public String deleteGame(@PathVariable("id") long id, Model model) {
        Optional<Game> gameOptional = gameRepository.findById(id);
        if (gameOptional.isEmpty()) {
            throw new IllegalArgumentException("Invalid Game");
        }
        gameRepository.delete(gameOptional.get());
        return "redirect:/games";
    }
}
