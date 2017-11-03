package com.example.modules.books.installers;

import com.example.modules.books.domain.author.Author;
import com.example.modules.books.domain.author.AuthorRepository;
import com.foreach.across.core.annotations.Installer;
import com.foreach.across.core.annotations.InstallerMethod;
import com.foreach.across.core.installers.InstallerPhase;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author Steven Gentens
 */
@Order(2)
@Installer(description = "Installs a number of sellers", phase = InstallerPhase.AfterModuleBootstrap)
@RequiredArgsConstructor
public class AuthorInstaller {

    private final AuthorRepository authorRepository;
    private final Random random = new Random();

    @InstallerMethod
    private void installSellers() {
        List<String> names = Arrays.asList( "Agatha Christie", "Dennis Lehane", "Wilkie Collins", "William Shakespeare", "Jay Asher", "Michael Connelly", "Christopher Paolini", "J. K. Rowling" );
        names.stream().map( name -> {
            String email = name.replace( " ", "." ).toLowerCase() + "@something.com";

            return Author.builder().name( name )
                    .email( email )
                    .gravatarUrl( "https://i.imgur.com/kkjULxY.png" )
                    .build();

        } ).forEach( authorRepository::save );
    }
}
