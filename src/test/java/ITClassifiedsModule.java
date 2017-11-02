import com.example.modules.classifieds.ClassifiedsModule;
import com.example.modules.classifieds.domain.category.Category;
import com.example.modules.classifieds.domain.category.CategoryRepository;
import com.example.modules.classifieds.domain.classified.Classified;
import com.example.modules.classifieds.domain.classified.ClassifiedRepository;
import com.example.modules.classifieds.domain.seller.Seller;
import com.example.modules.classifieds.domain.seller.SellerRepository;
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
public class ITClassifiedsModule {

    @Autowired
    private ClassifiedRepository classifiedRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void classifiedsShouldBeInstalled() {
        List<Classified> classifieds = classifiedRepository.findAll();
        assertEquals( 9, classifieds.size() );
    }

    @Test
    public void categoriesShouldBeInstaller() {
        List<Category> categories = categoryRepository.findAll();
        assertEquals( 3, categories.size() );
    }

    @Test
    public void sellersShouldBeInstalled() {
        List<Seller> sellers = sellerRepository.findAll();
        assertEquals( 6, sellers.size() );
    }

    @AcrossTestConfiguration(modules = {AcrossHibernateJpaModule.NAME})
    protected static class Config {
        @Bean
        public ClassifiedsModule classifiedsModule() {
            return new ClassifiedsModule();
        }
    }
}
