package org.krustef.librarymanagement.service;

import org.krustef.librarymanagement.models.Genre;
import org.krustef.librarymanagement.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {
    private final GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public Genre getGenreById(Long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre not found"));
    }

    public Genre saveGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    public Genre updateGenre(Long id, Genre updatedGenre) {
        return genreRepository.findById(id)
                .map(genre -> {
                    genre.setName(updatedGenre.getName());
                    return genreRepository.save(genre);
                })
                .orElseThrow(() -> new RuntimeException("Genre not found"));
    }

    public void deleteGenre(Long id) {
        if (genreRepository.existsById(id)) {
            genreRepository.deleteById(id);
        } else {
            throw new RuntimeException("Genre not found");
        }
    }

    public Optional<Genre> getGenreByName(String name) {
        return genreRepository.findByName(name);
    }
}