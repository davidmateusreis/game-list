package com.david.myvideogamelist;

import com.david.myvideogamelist.models.Game;
import com.david.myvideogamelist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Component
@Transactional
public class MyFirstGames implements CommandLineRunner {

    @Autowired
    private GameRepository gameRepository;

    @Override
    public void run(String... args) throws Exception {

        Game game1 = new Game("Super Mario World");
        game1.setReleaseDate(LocalDate.of(1990, 11, 21));
        game1.setDeveloper("Nintendo");
        game1.setPublisher("Nintendo");

        Game game2 = new Game("Super Metroid");
        game2.setReleaseDate(LocalDate.of(1994, 3, 19));
        game2.setDeveloper("Nintendo");
        game2.setPublisher("Nintendo");

        Game game3 = new Game("Donkey Kong Country");
        game3.setReleaseDate(LocalDate.of(1994, 11, 21));
        game3.setDeveloper("Rare");
        game3.setPublisher("Nintendo");

        Game game4 = new Game("The Legend of Zelda: A Link to the Past");
        game4.setReleaseDate(LocalDate.of(1991, 11, 21));
        game4.setDeveloper("Nintendo");
        game4.setPublisher("Nintendo");

        gameRepository.save(game1);
        gameRepository.save(game2);
        gameRepository.save(game3);
        gameRepository.save(game4);
    }
}
