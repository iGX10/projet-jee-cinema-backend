package org.ensetm.projetcinema;

import org.ensetm.projetcinema.models.Film;
import org.ensetm.projetcinema.models.Salle;
import org.ensetm.projetcinema.models.Ticket;
import org.ensetm.projetcinema.service.ICinemaInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class ProjetcinemaApplication implements CommandLineRunner {

    @Autowired private ICinemaInitService cinemaInitService;
    @Autowired private RepositoryRestConfiguration restConfiguration;

    public static void main(String[] args) {
        SpringApplication.run(ProjetcinemaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        restConfiguration.exposeIdsFor(Film.class, Salle.class, Ticket.class);
        cinemaInitService.initVilles();
        cinemaInitService.initCinemas();
        cinemaInitService.initSalles();
        cinemaInitService.initPlaces();
        cinemaInitService.initCategories();
        cinemaInitService.initFilms();
        cinemaInitService.initSeances();
        cinemaInitService.initProjections();
        cinemaInitService.initTickets();
    }
}
