package org.krustef.librarymanagement.repository;

import org.krustef.librarymanagement.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    // Search by Name
    @Query("SELECT b FROM Book b WHERE lower(b.name) LIKE lower(concat('%', :name, '%'))")
    List<Book> findByNameIgnoreCase(@Param("name") String name);

    // Search by Description
    @Query("SELECT b FROM Book b JOIN b.genres g WHERE lower(g.name) LIKE lower(concat('%', :description, '%'))")
    List<Book> findByDescriptionIgnoreCase(@Param("description") String description);

    // Search by Genre
    @Query("SELECT b FROM Book b JOIN b.genres g WHERE lower(g.name) LIKE lower(concat('%', :genre, '%'))")
    List<Book> findByGenreIgnoreCase(@Param("genre") String genre);
}
