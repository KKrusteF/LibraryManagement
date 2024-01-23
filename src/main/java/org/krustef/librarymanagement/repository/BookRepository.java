package org.krustef.librarymanagement.repository;

import org.krustef.librarymanagement.models.Author;
import org.krustef.librarymanagement.models.Book;
import org.krustef.librarymanagement.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitle(String title);

    List<Book> findByGenre(Genre genre);

    @Query("SELECT b FROM Book b JOIN b.authors a WHERE a = :author")
    List<Book> findByAuthors(@Param("author") Author author);
}
