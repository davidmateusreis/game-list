package com.david.gamelist;

import com.david.gamelist.models.Developer;
import com.david.gamelist.models.Game;
import com.david.gamelist.models.Publisher;
import com.david.gamelist.repositories.DeveloperRepository;
import com.david.gamelist.repositories.GameRepository;
import com.david.gamelist.repositories.PublisherRepository;
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
    @Autowired
    private DeveloperRepository developerRepository;
    @Autowired
    private PublisherRepository publisherRepository;

    @Override
    public void run(String... args) throws Exception {

        Developer dev1 = new Developer("Nintendo");
        Developer dev2 = new Developer("Capcom");
        Developer dev3 = new Developer("Square");
        Developer dev4 = new Developer("Rare");

        developerRepository.save(dev1);
        developerRepository.save(dev2);
        developerRepository.save(dev3);
        developerRepository.save(dev4);
        developerRepository.flush();

        Publisher pub1 = new Publisher("Nintendo");
        Publisher pub2 = new Publisher("Capcom");
        Publisher pub3 = new Publisher("Square");
        Publisher pub4 = new Publisher("Rare");

        publisherRepository.save(pub1);
        publisherRepository.save(pub2);
        publisherRepository.save(pub3);
        publisherRepository.save(pub4);
        publisherRepository.flush();

        Game game1 = new Game("Super Mario World");
        game1.setReleaseDate(LocalDate.of(1990, 11, 21));
        game1.setGenre("Platformer");
        game1.setRegion("Japan");
        game1.setDeveloper(dev1);
        game1.setPublisher(pub1);

        Game game2 = new Game("Super Metroid");
        game2.setReleaseDate(LocalDate.of(1994, 3, 19));
        game2.setGenre("Platformer");
        game2.setRegion("Japan");
        game2.setDeveloper(dev1);
        game2.setPublisher(pub1);

        Game game3 = new Game("Donkey Kong Country");
        game3.setReleaseDate(LocalDate.of(1994, 11, 21));
        game3.setGenre("Platformer");
        game3.setRegion("Japan");
        game3.setDeveloper(dev1);
        game3.setPublisher(pub4);

        Game game4 = new Game("The Legend of Zelda: A Link to the Past");
        game4.setReleaseDate(LocalDate.of(1991, 11, 21));
        game4.setGenre("Platformer");
        game4.setRegion("Japan");
        game4.setDeveloper(dev1);
        game4.setPublisher(pub1);

        gameRepository.save(game1);
        gameRepository.save(game2);
        gameRepository.save(game3);
        gameRepository.save(game4);

    }
}
