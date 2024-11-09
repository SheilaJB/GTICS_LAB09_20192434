package org.example.gtics_lab9_20192434.Controller;

import org.example.gtics_lab9_20192434.Daos.CoctelDao;
import org.example.gtics_lab9_20192434.Entity.Coctel;
import org.example.gtics_lab9_20192434.Entity.FavoriteCoctel;
import org.example.gtics_lab9_20192434.Reposiroty.FavoriteCoctelRepository;
import org.example.gtics_lab9_20192434.Response.CoctelResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/coctel")
public class HomeController {

    @Autowired
    private CoctelDao coctelDao;

    private final FavoriteCoctelRepository favoriteCoctelRepository;

    public HomeController(FavoriteCoctelRepository favoriteCoctelRepository) {
        this.favoriteCoctelRepository = favoriteCoctelRepository;
    }

    //Listamos los primeros 16 cocteles de la lista
    @GetMapping("/list")
    public String listarCocteles(Model model) {
        //Listamos
        model.addAttribute("cocteles", coctelDao.listarCocteles());
        return "index";
    }

    //Detalles del cóctel
    @GetMapping("/detail/{id}")
    public String detailCocteles(@PathVariable("id") String id, Model model) {
        Coctel coctel = coctelDao.buscarCoctel(id);
        model.addAttribute("coctel", coctel);
        return "detail";
    }

    //Guardar Favorite
    @PostMapping("/favorite/{id}")
    public String agregarAFavoritos(@PathVariable("id") String id) {
        // URL del servicio externo para obtener detalles del cóctel
        String url = "https://www.thecocktaildb.com/api/json/v1/1/lookup.php?i=" + id;

        // Utiliza RestTemplate para obtener los datos del cóctel desde la API externa
        RestTemplate restTemplate = new RestTemplate();
        CoctelResponse response = restTemplate.getForObject(url, CoctelResponse.class);

        if (response != null && !response.getDrinks().isEmpty()) {
            // Obtén el primer cóctel de la respuesta
            Coctel coctel = response.getDrinks().get(0);

            // Crea y guarda la entidad FavoriteCoctel con los detalles obtenidos
            FavoriteCoctel favoriteCoctel = new FavoriteCoctel();
            favoriteCoctel.setIdDrink(coctel.getIdDrink());
            favoriteCoctel.setStrDrink(coctel.getStrDrink());
            favoriteCoctel.setStrDrinkThumb(coctel.getStrDrinkThumb());
            favoriteCoctel.setFavorite(Boolean.parseBoolean("1"));

            favoriteCoctelRepository.save(favoriteCoctel);
            return "redirect:/coctel/list";
        } else {
            return "redirect:/coctel/detail/{id}";
        }
    }

}
