package org.example.gtics_lab9_20192434.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "favoriteCoctel")
public class FavoriteCoctel {

    @Id
    @Column(name = "id")
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
