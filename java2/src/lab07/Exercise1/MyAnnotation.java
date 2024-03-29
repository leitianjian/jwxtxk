package lab07.Exercise1;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Retention(RUNTIME)
@Target(FIELD)

public @interface MyAnnotation {
	int precision()default 0;
	
	String format() default "yyyy-MM-DD hh:mm:ss";
}
