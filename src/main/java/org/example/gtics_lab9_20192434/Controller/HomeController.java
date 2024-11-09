package org.example.gtics_lab9_20192434.Controller;

import org.example.gtics_lab9_20192434.Daos.CoctelDao;
import org.example.gtics_lab9_20192434.Entity.Coctel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/coctel")
public class HomeController {

    @Autowired
    private CoctelDao coctelDao;

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

}
