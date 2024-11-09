package org.example.gtics_lab9_20192434.Entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
@CrossOrigin
public class Coctel {
    private String strDrink;
    private String strDrinkThumb;
    private String idDrink;
    private String strDrinkAlternate;
    private String strTags;
    private String strVideo;
    private String strCategory;
    private String strIBA;
    private String strAlcoholic;
    private String strGlass;
    private String strInstructions;
    private String strInstructionsES;
    private String strIngredient1;
    private String strIngredient2;
    private String strIngredient3;
    private String strIngredient4;
    private String strIngredient5;
    private String strIngredient6;
    private String strIngredient7;
    private String strIngredient8;
    private String strIngredient9;
    private String strIngredient10;
    private String strMeasure1;
    private String strMeasure2;
    private String strMeasure3;
    private String strMeasure4;
    private String strMeasure5;
    private String strMeasure6;
    private String strMeasure7;
    private String strMeasure8;
    private String strMeasure9;
    private String strMeasure10;
    private String strImageSource;
    private String strImageAttribution;
    private String dateModified;

    public List<String> getIngredients() {
        return Arrays.asList( strIngredient1, strIngredient2, strIngredient3, strIngredient4, strIngredient5,
                strIngredient6, strIngredient7, strIngredient8, strIngredient9, strIngredient10 ).stream().filter(Objects::nonNull).filter(s -> !s.isEmpty()).collect(Collectors.toList());
    }

    public List<String> getMeasures() {
        return Arrays.asList( strMeasure1, strMeasure2, strMeasure3, strMeasure4, strMeasure5, strMeasure6,
                strMeasure7, strMeasure8, strMeasure9, strMeasure10 ).stream().filter(Objects::nonNull).filter(s -> !s.isEmpty()).collect(Collectors.toList());
    }
}
