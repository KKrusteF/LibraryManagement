package org.krustef.librarymanagement.repository;

import org.krustef.librarymanagement.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

}
