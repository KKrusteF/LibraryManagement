package org.krustef.librarymanagement.service.impl;

import org.krustef.librarymanagement.exception.NotFoundException;
import org.krustef.librarymanagement.models.Genre;
import org.krustef.librarymanagement.repository.GenreRepository;
import org.krustef.librarymanagement.service.GenreService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public List<Genre> findAllGenres() {
        return genreRepository.findAll();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public Genre findGenreById(Long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Genre not found with ID %d", id)));
    }

    @Override
    public void createGenre(Genre genre) {
        genreRepository.save(genre);
    }

    @Override
    public void updateGenre(Genre genre) {
        genreRepository.save(genre);
    }

    @Override
    public void deleteGenre(Long id) {
        final Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Genre not found with ID %d", id)));

        genreRepository.deleteById(genre.getId());
    }
}
