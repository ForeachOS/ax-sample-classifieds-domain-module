package com.example.modules.books.installers;

import com.example.modules.books.domain.author.AuthorRepository;
import com.example.modules.books.domain.book.Book;
import com.example.modules.books.domain.book.BookRepository;
import com.example.modules.books.domain.book.Status;
import com.example.modules.books.domain.genre.Genre;
import com.example.modules.books.domain.genre.GenreRepository;
import com.foreach.across.core.annotations.Installer;
import com.foreach.across.core.annotations.InstallerMethod;
import com.foreach.across.core.installers.InstallerPhase;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Steven Gentens
 */
@Order(3)
@Installer(description = "Installs a number of books", phase = InstallerPhase.AfterModuleBootstrap)
@RequiredArgsConstructor
public class BookInstaller {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final ThreadLocalRandom random = ThreadLocalRandom.current();

    private Status[] status = Status.values();

    @InstallerMethod
    private void installMysteryBooks() {
        List<Book> books = new ArrayList<>();
        Genre mystery = genreRepository.findGenreByName( "Mystery" );

        books.add(
                Book.builder()
                        .title( "And Then There Were None" )
                        .price( BigDecimal.valueOf( 47.99 ) )
                        .description( "And Then There Were None is a mystery novel by English writer Agatha Christie, widely considered her masterpiece and described by her as the most difficult of her books to write. It was first published in the United Kingdom by the Collins Crime Club on 6 November 1939, as Ten Little Niggers, after the British blackface song, which serves as a major plot point. The US edition was not released until December 1939; its American reprints and adaptations were all retitled And Then There Were None, after the last five words in the nursery rhyme \"Ten Little Indians\"." )
                        .author( authorRepository.findOneByName( "Agatha Christie" ) )
                        .build() );
        books.add( Book.builder()
                .title( "Shutter Island" )
                .price( BigDecimal.valueOf( 29.00 ) )
                .description( "Shutter Island is a best-selling novel by Dennis Lehane. Lehane has said he sought to write a novel that would be a homage to Gothic settings, B movies, and pulp. He described the novel as a hybrid of the works of the Brontë sisters and the 1956 film Invasion of the Body Snatchers. His intent was to write the main characters in a position where they would lack 20th century resources such as radio communications. He also structured the book to be more taut than his previous book, Mystic River" )
                .author( authorRepository.findOneByName( "Dennis Lehane" ) )
                .build()
        );
        books.add( Book.builder()
                .title( "The Woman In White" )
                .price( BigDecimal.valueOf( 35.00 ) )
                .description( "The Woman in White is Wilkie Collins' fifth published novel. It is considered to be among the first mystery novels and is widely regarded as one of the first (and finest) in the genre of \"sensation novels\".\n" +
                        "\n" +
                        "The story is sometimes considered an early example of detective fiction with protagonist Walter Hartright employing many of the sleuthing techniques of later private detectives. The use of multiple narrators (including nearly all the principal characters) draws on Collins's legal training, and as he points out in his Preamble: \"the story here presented will be told by more than one pen, as the story of an offence against the laws is told in Court by more than one witness\". In 2003, Robert McCrum writing for The Observer listed The Woman in White number 23 in \"the top 100 greatest novels of all time\", and the novel was listed at number 77 on the BBC's survey The Big Read." )
                .author( authorRepository.findOneByName( "Wilkie Collins" ) )
                .build() );


        books.forEach( book -> {
            bookRepository.save( book.toBuilder()
                    .genre( mystery )
                    .status( getRandomStatus() )
                    .publicationDate( getRandomDate() )
                    .build() );
        } );

    }

    @InstallerMethod
    private void installDramaBooks() {
        Genre drama = genreRepository.findGenreByName( "Drama" );
        List<Book> books = new ArrayList<>();

        if (drama != null) {
            books.add(
                    Book.builder()
                            .title( "Hamlet" )
                            .price( BigDecimal.valueOf( 19.990 ) )
                            .author( authorRepository.findOneByName( "William Shakespeare" ) )
                            .description( "The Tragedy of Hamlet, Prince of Denmark, often shortened to Hamlet (/ˈhæmlɪt/), is a tragedy written by William Shakespeare. Set in Denmark, the play dramatises the revenge Prince Hamlet is called to wreak upon his uncle, Claudius, by the ghost of Hamlet's father, King Hamlet. Claudius had murdered his own brother and seized the throne, also marrying his deceased brother's widow.\n" +
                                    "\n" +
                                    "Hamlet is Shakespeare's longest play, and is considered among the most powerful and influential works of world literature, with a story capable of \"seemingly endless retelling and adaptation by others\". The play likely was one of Shakespeare's most popular works during his lifetime, and still ranks among his most performed, topping the performance list of the Royal Shakespeare Company and its predecessors in Stratford-upon-Avon since 1879. It has inspired many other writers—from Johann Wolfgang von Goethe and Charles Dickens to James Joyce and Iris Murdoch—and has been described as \"the world's most filmed story after Cinderella\"." )
                            .build()
            );

            books.add(
                    Book.builder()
                            .title( "A Midsummer Night's Dream" )
                            .author( authorRepository.findOneByName( "William Shakespeare" ) )
                            .price( BigDecimal.valueOf( 9.99 ) )
                            .description( "A Midsummer Night's Dream is a comedy written by William Shakespeare. It portrays the events surrounding the marriage of Theseus, the Duke of Athens, to Hippolyta, the former queen of the Amazons. These include the adventures of four young Athenian lovers and a group of six amateur actors (the mechanicals) who are controlled and manipulated by the fairies who inhabit the forest in which most of the play is set. The play is one of Shakespeare's most popular works for the stage and is widely performed across the world." )
                            .build()
            );

            books.add(
                    Book.builder()
                            .title( "Thirteen Reasons Why" )
                            .price( BigDecimal.valueOf( 24.99 ) )
                            .author( authorRepository.findOneByName( "Jay Asher" ) )
                            .description( "Thirteen Reasons Why is a young adult novel written by Jay Asher. It is the story of a young high school student as she descends into despair brought on by betrayal and bullying, culminating with her suicide. She details the thirteen reasons why in an audio diary which was mailed to a friend two weeks after her death.\n" +
                                    "\n" +
                                    "Thirteen Reasons Why has received recognition and awards from several young adult literary associations, and the paperback edition reached #1 on the New York Times Best Seller list. A screenplay was written, based on the original release of the book, that became the basis of the dramatic television series 13 Reasons Why released through Netflix. The screenplay contains several deviations from the book, including, but not limited to, name changes, plot elements and character personalities." )
                            .build()
            );

            books.forEach( book -> {
                bookRepository.save( book.toBuilder()
                        .genre( drama )
                        .status( getRandomStatus() )
                        .publicationDate( getRandomDate() )
                        .build() );
            } );
        }
    }

    @InstallerMethod
    private void installFantasyBooks() {
        Genre fantasy = genreRepository.findGenreByName( "Fantasy" );
        List<Book> books = new ArrayList<>();
        if (fantasy != null) {

            books.add( Book.builder()
                    .author( authorRepository.findOneByName( "Michael Connelly" ) )
                    .title( "City of Bones" )
                    .price( BigDecimal.valueOf( 15.0 ) )
                    .description( "City of Bones is the twelfth novel by American crime author Michael Connelly, and the eighth featuring the Los Angeles detective Hieronymus \"Harry\" Bosch. It was named a Notable Book of the Year by the New York Times." )
                    .build() );

            books.add(
                    Book.builder()
                            .author( authorRepository.findOneByName( "Christopher Paolini" ) )
                            .title( "Eragon" )
                            .price( BigDecimal.valueOf( 37.0 ) )
                            .description( "Eragon is the first novel in the Inheritance Cycle by Christopher Paolini. After writing the first draft for a year, Paolini spent a second year rewriting and fleshing out the story and characters. His parents saw the final manuscript and decided to self-publish Eragon. Paolini spent a year traveling around the United States promoting the novel. By chance, the book was discovered by Carl Hiaasen, who got it re-published by Alfred A. Knopf" )
                            .build()
            );

            books.add(
                    Book.builder()
                            .author( authorRepository.findOneByName( "J. K. Rowling" ) )
                            .title( "Harry Potter" )
                            .price( BigDecimal.valueOf( 49.0 ) )
                            .description( "Harry Potter is a series of fantasy novels written by British author J. K. Rowling. The novels chronicle the life of a young wizard, Harry Potter, and his friends Hermione Granger and Ron Weasley, all of whom are students at Hogwarts School of Witchcraft and Wizardry. The main story arc concerns Harry's struggle against Lord Voldemort, a dark wizard who intends to become immortal, overthrow the wizard governing body known as the Ministry of Magic, and subjugate all wizards and muggles, a reference term that means non-magical people" )
                            .build()
            );

            books.forEach( book -> {
                bookRepository.save( book.toBuilder()
                        .genre( fantasy )
                        .status( getRandomStatus() )
                        .publicationDate( getRandomDate() )
                        .build() );
            } );
        }
    }

    private Status getRandomStatus() {
        return status[random.nextInt( status.length )];
    }


    private Date getRandomDate() {
        Date start = getBeforeAfterDate( new Date(), -7 );
        Date end = getBeforeAfterDate( new Date(), 7 );
        return new Date( random.nextLong( start.getTime(), end.getTime() ) );
    }

    private Date getBeforeAfterDate(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime( date );
        calendar.add( Calendar.DATE, days );
        return calendar.getTime();
    }
}
