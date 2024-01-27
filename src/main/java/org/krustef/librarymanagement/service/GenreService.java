package org.krustef.librarymanagement.service;

import org.krustef.librarymanagement.models.Genre;

import java.util.List;

public interface GenreService {

	List<Genre> findAllGenres();

	Genre findGenreById(Long id);

	void createGenre(Genre genre);

	void updateGenre(Genre genre);

	void deleteGenre(Long id);
}
