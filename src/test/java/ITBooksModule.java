import com.example.modules.books.BooksModule;
import com.example.modules.books.domain.author.Author;
import com.example.modules.books.domain.author.AuthorRepository;
import com.example.modules.books.domain.book.Book;
import com.example.modules.books.domain.book.BookRepository;
import com.example.modules.books.domain.genre.Genre;
import com.example.modules.books.domain.genre.GenreRepository;
import com.foreach.across.modules.hibernate.jpa.AcrossHibernateJpaModule;
import com.foreach.across.test.AcrossTestConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Steven Gentens
 */
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
@ContextConfiguration
public class ITBooksModule {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Test
    public void classifiedsShouldBeInstalled() {
        List<Book> books = bookRepository.findAll();
        assertEquals( 9, books.size() );
    }

    @Test
    public void categoriesShouldBeInstaller() {
        List<Genre> categories = genreRepository.findAll();
        assertEquals( 3, categories.size() );
    }

    @Test
    public void sellersShouldBeInstalled() {
        List<Author> authors = authorRepository.findAll();
        assertEquals( 8, authors.size() );
    }

    @AcrossTestConfiguration(modules = {AcrossHibernateJpaModule.NAME})
    protected static class Config {
        @Bean
        public BooksModule classifiedsModule() {
            return new BooksModule();
        }
    }
}
