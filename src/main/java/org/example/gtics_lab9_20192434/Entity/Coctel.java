package org.example.gtics_lab9_20192434.Entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.CrossOrigin;

@Getter
@Setter
@CrossOrigin
public class Coctel {
    private String strDrink;
    private String strDrinkThumb;
    private String idDrink;
}
