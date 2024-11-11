package org.example.gtics_lab9_20192434.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "favoriteCoctel")
public class Favoritecoctel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "idDrink", nullable = false, length = 50)
    private String idDrink;

    @Column(name = "strDrink", length = 255)
    private String strDrink;

    @Column(name = "strDrinkThumb", length = 255)
    private String strDrinkThumb;

    @Column(name = "isFavorite")
    private boolean isFavorite;
}
