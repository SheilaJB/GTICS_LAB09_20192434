package org.example.gtics_lab9_20192434.Daos;

import org.example.gtics_lab9_20192434.Entity.Coctel;
import org.example.gtics_lab9_20192434.Response.CoctelResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CoctelDao {

    //Listar todos los cocteles
    public List<Coctel> listarCocteles() {

        //Asignamos el resultado de una solicitud rest a un objeto java
        RestTemplate restTemplate = new RestTemplate();
        //Obtenemos los parámetros del coctel (nombre/imagen/ID) y lo pasamos a una lista
        ResponseEntity<CoctelResponse> response = restTemplate.getForEntity(
                "https://www.thecocktaildb.com/api/json/v1/1/filter.php?c=Cocktail", CoctelResponse.class
        );

        //Obtenemos los primeros 12 cocteles
        return response.getBody().getDrinks().stream().limit(12).collect(Collectors.toList());

    }

    //Obtener detalle de un coctel
    public Coctel buscarCoctel(String id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CoctelResponse> response = restTemplate.getForEntity(
                "https://www.thecocktaildb.com/api/json/v1/1/lookup.php?i=" + id, CoctelResponse.class
        );
        return response.getBody().getDrinks().get(0);
    }
}
