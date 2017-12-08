package org.djr.elastic;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by djr4488 on 12/8/17.
 */
@Retention(RUNTIME)
@Target({FIELD})
public @interface ElasticSearchConfig {
    String hostsPropertyName();
    String portsPropertyName();
    String schemePropertyName();
    String delineatorPropertyName();
}
