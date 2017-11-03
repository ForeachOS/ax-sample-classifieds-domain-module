package com.example.modules.books.installers;

import com.example.modules.books.domain.genre.Genre;
import com.example.modules.books.domain.genre.GenreRepository;
import com.foreach.across.core.annotations.Installer;
import com.foreach.across.core.annotations.InstallerMethod;
import com.foreach.across.core.installers.InstallerPhase;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;

import java.util.Arrays;
import java.util.List;

/**
 * @author Steven Gentens
 */
@Order(1)
@Installer(description = "Installs a number of categories", phase = InstallerPhase.AfterModuleBootstrap)
@RequiredArgsConstructor
public class GenreInstaller {

    private final GenreRepository genreRepository;

    @InstallerMethod
    private void installCategories() {
        List<String> names = Arrays.asList( "Mystery", "Drama", "Fantasy" );
        names.stream().map( name -> Genre.builder().name( name ).build() ).forEach( genreRepository::save );
    }
}
