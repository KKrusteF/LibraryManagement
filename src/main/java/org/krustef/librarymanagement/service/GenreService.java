package org.krustef.librarymanagement.service;

import org.krustef.librarymanagement.dto.GenreDTO;
import org.krustef.librarymanagement.models.Genre;
import org.krustef.librarymanagement.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<GenreDTO> getAllGenresDTO() {
        return genreRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public GenreDTO getGenreDTOById(Long genreId) {
        Genre genre = genreRepository.findById(genreId).orElse(null);
        return (genre != null) ? mapToDTO(genre) : null;
    }

    public GenreDTO saveGenreDTO(GenreDTO genreDTO) {
        Genre genre = mapToEntity(genreDTO);
        return mapToDTO(genreRepository.save(genre));
    }

    public void deleteGenreById(Long genreId) {
        genreRepository.deleteById(genreId);
    }

    private GenreDTO mapToDTO(Genre genre) {
        return new GenreDTO(
                genre.getGenreId(),
                genre.getGenreName()
        );
    }

    private Genre mapToEntity(GenreDTO genreDTO) {
        Genre genre = new Genre();
        genre.setGenreId(genreDTO.getGenreId());
        genre.setGenreName(genreDTO.getGenreName());
        return genre;
    }
}
