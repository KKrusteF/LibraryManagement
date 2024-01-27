package org.krustef.librarymanagement.service;

import org.krustef.librarymanagement.models.Author;

import java.util.List;

public interface AuthorService {

    List<Author> findAllAuthors();

    Author findAuthorById(Long id);

    void createAuthor(Author author);

    void updateAuthor(Author author);

    void deleteAuthor(Long id);

}
