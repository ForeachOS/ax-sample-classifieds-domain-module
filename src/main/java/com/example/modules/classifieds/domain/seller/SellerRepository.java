package com.example.modules.classifieds.domain.seller;

import com.foreach.across.core.annotations.Exposed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Steven Gentens
 */
@Exposed
public interface SellerRepository extends JpaRepository<Seller, Long>, JpaSpecificationExecutor<Seller> {
}
