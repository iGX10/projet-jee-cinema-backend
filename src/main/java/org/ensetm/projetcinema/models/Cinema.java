package org.ensetm.projetcinema.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Cinema implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double longitude;
    private double latitude;
    private double altitude;
    private int nombreSalles;
    @ManyToOne
    private Ville ville;
    @OneToMany(mappedBy = "cinema")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Salle> salles;
}
