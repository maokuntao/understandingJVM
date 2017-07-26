package com.taomk.understandingJVM.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 * @author taomk 2017年7月26日 下午8:28:16
 *
 */
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface Greeting {

	/**
	 * 自定义信息
	 * @return
	 */
	public String message() default "~";
	
	/**
	 * 打招呼
	 * @return
	 */
	public String sayHi() default "Hi~";
}
