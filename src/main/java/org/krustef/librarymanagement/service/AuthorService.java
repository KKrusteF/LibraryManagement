package org.krustef.librarymanagement.service;

import org.krustef.librarymanagement.models.Author;
import org.krustef.librarymanagement.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long id) throws RuntimeException {
        return authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Author not found: " + id));
    }

    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    public void deleteAuthor(Long id) throws RuntimeException {
        if(authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
        } else {
            throw new RuntimeException("Author not found: " + id);
        }
    }
}
