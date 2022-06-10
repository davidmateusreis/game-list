package com.david.myvideogamelist.controllers;

import com.david.myvideogamelist.dtos.AutoFillDTO;
import com.david.myvideogamelist.models.Game;
import com.david.myvideogamelist.models.Publisher;
import com.david.myvideogamelist.repositories.DeveloperRepository;
import com.david.myvideogamelist.repositories.GameRepository;
import com.david.myvideogamelist.repositories.PublisherRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class GameController {


    private GameRepository gameRepository;
    private DeveloperRepository developerRepository;
    private PublisherRepository publisherRepository;

    private List<Publisher> suggestsPublishers = new ArrayList<>(); //sugestões

    public GameController(GameRepository gameRepository, DeveloperRepository developerRepository, PublisherRepository publisherRepository) {
        this.gameRepository = gameRepository;
        this.developerRepository = developerRepository;
        this.publisherRepository = publisherRepository;
    }

    @GetMapping("/games")
    public String games(Model model) {

        model.addAttribute("gameList", gameRepository.findAll());
        return "games/index";
    }

    @GetMapping("/games/new")
    public String newGame(Model model) {
        model.addAttribute("game", new Game(""));
        model.addAttribute("developers", developerRepository.findAll());
        return "/games/form";
    }

    @PostMapping("/games/save")
    public String addGame(@Valid @ModelAttribute("game") Game game, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("developers", developerRepository.findAll());
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
        model.addAttribute("developers", developerRepository.findAll());
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

    @RequestMapping("/games/publisherNameAutoFill")
    @ResponseBody
    public List<AutoFillDTO> publisherNameAutoFill(@RequestParam("term") String term) { //retorna o termo buscado
        List<AutoFillDTO> suggests = new ArrayList<>();
        try {
            if (term.length() == 3) {
                suggestsPublishers = publisherRepository.searchByName(term);
            }
            for (Publisher publisher : suggestsPublishers) {
                if (publisher.getName().toLowerCase().contains(term.toLowerCase())) {
                    AutoFillDTO dto = new AutoFillDTO(publisher.getName(), Long.toString(publisher.getId()));
                    suggests.add(dto);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return suggests;
    }
}
