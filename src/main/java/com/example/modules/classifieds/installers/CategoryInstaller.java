package com.example.modules.classifieds.installers;

import com.example.modules.classifieds.domain.category.Category;
import com.example.modules.classifieds.domain.category.CategoryRepository;
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
public class CategoryInstaller {

    private final CategoryRepository categoryRepository;

    @InstallerMethod
    private void installCategories() {
        List<String> names = Arrays.asList( "Sports & Gear", "Music & Instruments", "Electronics" );
        names.stream().map( name -> Category.builder().name( name ).build() ).forEach( categoryRepository::save );
    }
}
