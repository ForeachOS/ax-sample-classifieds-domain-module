package com.example.modules.books.domain.book;

import com.example.modules.books.domain.author.Author;
import com.example.modules.books.domain.genre.Genre;
import com.foreach.across.core.annotations.Exposed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author Steven Gentens
 */
@Exposed
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    List<Book> findAllByGenre(Genre category);

    List<Book> findAllByAuthor(Author author);

    List<Book> findAllByGenreOrderByIdDesc(Genre category);

    List<Book> findTop3ByOrderByIdDesc();
}
