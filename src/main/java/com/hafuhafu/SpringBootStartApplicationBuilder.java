package com.hafuhafu;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Created with Intellij IDEA.
 * Description:
 * Author: stone
 * Date: 2018-03-26
 * Time: 16:07
 */
public class SpringBootStartApplicationBuilder extends SpringBootServletInitializer {
    //使用外部servlet容器的接口
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MetroApplication.class);
    }
}
