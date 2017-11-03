package com.example.modules.books.config;

import com.example.modules.books.domain.Domain;
import com.foreach.across.modules.hibernate.jpa.repositories.config.EnableAcrossJpaRepositories;
import org.springframework.context.annotation.Configuration;

/**
 * @author Steven Gentens
 * @since 1.0.0
 */
@Configuration
@EnableAcrossJpaRepositories(basePackageClasses = Domain.class)
public class DomainConfiguration {
}
