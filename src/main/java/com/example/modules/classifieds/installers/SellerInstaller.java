package com.example.modules.classifieds.installers;

import com.example.modules.classifieds.domain.seller.Seller;
import com.example.modules.classifieds.domain.seller.SellerRepository;
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
public class SellerInstaller {

    private final SellerRepository sellerRepository;
    private final Random random = new Random();

    @InstallerMethod
    private void installSellers() {
        List<String> names = Arrays.asList( "Joshua Johnson", "Joe Edwards", "Meredith Baker", "Sophia Bennett", "Jane Doe", "Oliver Green" );
        List<String> emailProviders = Arrays.asList( "@facebook.com", "@outlook.com", "@yahoo.com" );
        names.stream().map( name -> {
            String email = name.replace( " ", "." ).toLowerCase()
                    + emailProviders.get( random.nextInt( emailProviders.size() ) );

            return Seller.builder().name( name )
                    .email( email )
                    .gravatarUrl( "" )
                    .build();

        } ).forEach( sellerRepository::save );
    }
}
