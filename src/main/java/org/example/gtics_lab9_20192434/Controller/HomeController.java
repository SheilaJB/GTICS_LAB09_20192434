package org.example.gtics_lab9_20192434.Controller;

import org.example.gtics_lab9_20192434.Daos.CoctelDao;
import org.example.gtics_lab9_20192434.Entity.Coctel;
import org.example.gtics_lab9_20192434.Entity.Favoritecoctel;
import org.example.gtics_lab9_20192434.Repository.FavoritecoctelRepository;
import org.example.gtics_lab9_20192434.Response.CoctelResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/coctel")
public class HomeController {

    private final FavoritecoctelRepository favoritecoctelRepository;
    @Autowired
    private CoctelDao coctelDao;
   // @Autowired
    public HomeController(FavoritecoctelRepository favoritecoctelRepository) {
        this.favoritecoctelRepository = favoritecoctelRepository;
    }
    //@Autowired


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
    public String agregarAFavoritos(@PathVariable("id") String id, RedirectAttributes attr) {
        // URL del servicio externo para obtener detalles del cóctel
        String url = "https://www.thecocktaildb.com/api/json/v1/1/lookup.php?i=" + id;

        // Utiliza RestTemplate para obtener los datos del cóctel desde la API externa
        RestTemplate restTemplate = new RestTemplate();
        CoctelResponse response = restTemplate.getForObject(url, CoctelResponse.class);

        if (response != null && !response.getDrinks().isEmpty()) {
            // Obtén el primer cóctel de la respuesta
            Coctel coctel = response.getDrinks().get(0);

            // Crea y guarda la entidad FavoriteCoctel con los detalles obtenidos
            Favoritecoctel favoriteCoctel = new Favoritecoctel();
            favoriteCoctel.setIdDrink(coctel.getIdDrink());
            favoriteCoctel.setStrDrink(coctel.getStrDrink());
            favoriteCoctel.setStrDrinkThumb(coctel.getStrDrinkThumb());
            favoriteCoctel.setFavorite(Boolean.parseBoolean("1"));

            favoritecoctelRepository.save(favoriteCoctel);
            attr.addFlashAttribute("messageOk", "Favorite Cocktail saved successfully!");
            return "redirect:/coctel/detail/" + id;
        } else {
            attr.addFlashAttribute("messageFail", "This drink is already in your favorites.");
            return "redirect:/coctel/detail/" + id;
        }
    }

    //Lista de favoritos
    @GetMapping("/listFavorite")
    public String listarCoctelesFavoritos(Model model) {
        //Listamos
        List<Favoritecoctel> lista = favoritecoctelRepository.findAll();
        if (lista.isEmpty()) {
            model.addAttribute("cocteles", lista);

        }else {
            model.addAttribute("cocteles", lista);
        }

        return "favorite";
    }

}
