package proj.emcegom.quality.assess.annotation;


import proj.emcegom.quality.assess.enums.BusinessType;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    BusinessType businessType() default BusinessType.OTHER;
    String title() default "";
}
