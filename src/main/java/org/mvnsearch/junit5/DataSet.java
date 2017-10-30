package org.mvnsearch.junit5;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * DataSet annotation
 *
 * @author linux_china
 */
@Target({TYPE, METHOD})
@Retention(RUNTIME)
@Inherited
public @interface DataSet {
    String[] value() default {};
}
