package com.example.modules.classifieds;

import com.foreach.across.core.AcrossModule;
import com.foreach.across.core.annotations.AcrossDepends;
import com.foreach.across.modules.hibernate.jpa.AcrossHibernateJpaModule;
import org.springframework.context.annotation.DependsOn;

/**
 * @author Steven Gentens
 */
@AcrossDepends(required=AcrossHibernateJpaModule.NAME)
public class ClassifiedsModule extends AcrossModule{
    public static final String NAME = "AcrossClassifiedsModule";
    @Override
    public String getName() {
        return NAME;
    }
}
