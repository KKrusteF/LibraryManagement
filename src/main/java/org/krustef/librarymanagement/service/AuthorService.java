package org.krustef.librarymanagement.service;

import org.krustef.librarymanagement.dto.AuthorDTO;
import org.krustef.librarymanagement.models.Author;
import org.krustef.librarymanagement.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<AuthorDTO> getAllAuthorsDTO() {
        return authorRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public AuthorDTO getAuthorDTOById(Long authorId) {
        Author author = authorRepository.findById(authorId).orElse(null);
        return (author != null) ? mapToDTO(author) : null;
    }

    public AuthorDTO saveAuthorDTO(AuthorDTO authorDTO) {
        Author author = mapToEntity(authorDTO);
        return mapToDTO(authorRepository.save(author));
    }

    public void deleteAuthorById(Long authorId) {
        authorRepository.deleteById(authorId);
    }

    private AuthorDTO mapToDTO(Author author) {
        return new AuthorDTO(
                author.getAuthorId(),
                author.getAuthorName(),
                author.getBirthDate(),
                author.getNationality()
        );
    }

    private Author mapToEntity(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setAuthorId(authorDTO.getAuthorId());
        author.setAuthorName(authorDTO.getAuthorName());
        author.setBirthDate(authorDTO.getBirthDate());
        author.setNationality(authorDTO.getNationality());
        return author;
    }
}
