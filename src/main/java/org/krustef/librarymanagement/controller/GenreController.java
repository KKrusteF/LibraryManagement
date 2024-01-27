package org.krustef.librarymanagement.controller;

import org.krustef.librarymanagement.models.Genre;
import org.krustef.librarymanagement.service.GenreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class GenreController {
    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @RequestMapping("/genres")
    public String findAllGenres(Model model) {
        final List<Genre> genres = genreService.findAllGenres();

        model.addAttribute("genres", genres);
        return "list-genres";
    }

    @RequestMapping("/genre/{id}")
    public String findGenreById(@PathVariable("id") Long id, Model model) {
        final Genre genre = genreService.findGenreById(id);

        model.addAttribute("genre", genre);
        return "list-genre";
    }

    @GetMapping("/addGenre")
    public String showCreateForm(Genre genre) {
        return "add-genre";
    }

    @RequestMapping("/add-genre")
    public String createCategory(Genre genre, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-genre";
        }

        genreService.createGenre(genre);
        model.addAttribute("genre", genreService.findAllGenres());
        return "redirect:/genres";
    }

    @GetMapping("/updateGenre/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        final Genre genre = genreService.findGenreById(id);

        model.addAttribute("genre", genre);
        return "update-genre";
    }

    @RequestMapping("/update-genre/{id}")
    public String updateCategory(@PathVariable("id") Long id, Genre genre, BindingResult result, Model model) {
        if (result.hasErrors()) {
            genre.setId(id);
            return "update-genre";
        }

        genreService.updateGenre(genre);
        model.addAttribute("genre", genreService.findAllGenres());
        return "redirect:/genres";
    }

    @RequestMapping("/remove-genre/{id}")
    public String deleteGenre(@PathVariable("id") Long id, Model model) {
        genreService.deleteGenre(id);

        model.addAttribute("genre", genreService.findAllGenres());
        return "redirect:/genres";
    }

}
