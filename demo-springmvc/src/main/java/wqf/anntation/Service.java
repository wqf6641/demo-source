package wqf.anntation;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)//反射
@Documented //javadoc
public @interface Service {
    String value() default "";
}
