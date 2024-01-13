package org.krustef.librarymanagement.repository;

import org.krustef.librarymanagement.models.Author;
import org.krustef.librarymanagement.models.Book;
import org.krustef.librarymanagement.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthor(Author author);
    List<Book> findByGenre(Genre genre);
}
