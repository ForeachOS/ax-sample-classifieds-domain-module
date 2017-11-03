package com.example.modules.books;

import com.foreach.across.core.AcrossModule;
import com.foreach.across.core.annotations.AcrossDepends;
import com.foreach.across.modules.hibernate.jpa.AcrossHibernateJpaModule;

/**
 * @author Steven Gentens
 */
@AcrossDepends(required=AcrossHibernateJpaModule.NAME)
public class BooksModule extends AcrossModule {
    public static final String NAME = "BooksModule";
    @Override
    public String getName() {
        return NAME;
    }
}
