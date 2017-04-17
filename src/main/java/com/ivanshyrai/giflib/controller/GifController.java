package com.ivanshyrai.giflib.controller;

import com.ivanshyrai.giflib.data.GifRepository;
import com.ivanshyrai.giflib.model.Gif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class GifController {

    @Autowired
    private GifRepository gifRepository;

    @RequestMapping("/")
    public String ListGifs(ModelMap modelMap) {
        List<Gif> allGifs = gifRepository.getAllGifs();
        modelMap.put("gifs",allGifs);
        return "home";
    }

    @RequestMapping("/gif/{name}")
    public String gifDetails(@PathVariable String name, ModelMap modelMap) {
        Gif gif = gifRepository.findByName(name);
        modelMap.put("gif",gif);
        return "gif-details";
    }

    @RequestMapping("/favorites")
    public String listFavorites(ModelMap modelMap) {
        List<Gif> gifs = gifRepository.findAllFavorites();
        modelMap.put("gifs",gifs);
        return "favorites";
    }

    @RequestMapping("/search")
    public String searchForGifs(@RequestParam String q, ModelMap modelMap) {
        List<Gif> gifs = gifRepository.findGifsByName(q);
        modelMap.put("gifs",gifs);
        modelMap.put("q",q);
        return "search-results";
    }
}
