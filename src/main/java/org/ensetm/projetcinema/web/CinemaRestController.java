package org.ensetm.projetcinema.web;

import lombok.Data;
import org.ensetm.projetcinema.dao.FilmRepository;
import org.ensetm.projetcinema.dao.TicketRepository;
import org.ensetm.projetcinema.models.Film;
import org.ensetm.projetcinema.models.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
public class CinemaRestController {

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping(path = "/imageFilm/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] image(@PathVariable(name = "id") Long id) throws IOException {
        Film film = filmRepository.findById(id).get();
        String photoName = film.getPhoto();

        File file = new File(System.getProperty("user.home")+"/projetJeeCinema/images/"+photoName);
        Path path = Paths.get(file.toURI());

        return Files.readAllBytes(path);
    }

    @PostMapping("/payerTickets")
    @Transactional
    public List<Ticket> payerTickets(@RequestBody TicketForm ticketForm) {
        List<Ticket> tickets = new ArrayList<>();

        ticketForm.getTickets().forEach(idTicket -> {
            Ticket ticket = ticketRepository.findById(idTicket).get();
            ticket.setNomClient(ticketForm.getNomClient());
            ticket.setCodePayement(ticketForm.getCodePaiement());
            ticket.setReserve(true);

            ticketRepository.save(ticket);
            tickets.add(ticket);
        });

        return tickets;
    }

}

@Data
class TicketForm {
    private String nomClient;
    private List<Long> tickets;
    private int codePaiement;
}
