package com.example.modules.classifieds.domain.classified;

import com.example.modules.classifieds.domain.category.Category;
import com.example.modules.classifieds.domain.seller.Seller;
import com.foreach.across.core.annotations.Exposed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author Steven Gentens
 */
@Exposed
public interface ClassifiedRepository extends JpaRepository<Classified, Long>, JpaSpecificationExecutor<Classified> {
    List<Classified> findAllByCategory(Category category);

    List<Classified> findAllBySeller(Seller seller);

    List<Classified> findAllByCategoryOrderByIdDesc(Category category);

    List<Classified> findTop3ByOrderByIdDesc();
}
