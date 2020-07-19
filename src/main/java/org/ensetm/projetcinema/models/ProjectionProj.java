package org.ensetm.projetcinema.models;

import org.springframework.data.rest.core.config.Projection;

import java.util.Collection;
import java.util.Date;

@Projection(name = "p1", types = org.ensetm.projetcinema.models.Projection.class)
public interface ProjectionProj {
    public Long getId();
    public Date getDateProjection();
    public double getPrix();
    public Film getFilm();
    public Salle getSalle();
    public Seance getSeance();
    public Collection<Ticket> getTickets();
}
