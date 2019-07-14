package team.exm.book.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import team.exm.book.web.filter.RequestInterceptor;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private RequestInterceptor filter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> list = new ArrayList();
        list.add("/login/**");
        list.add("/register/**");
        list.add("/**/**.js");
        list.add("/**/**.css");
        list.add("/**/**.jpeg");
        registry.addInterceptor(filter).addPathPatterns("/**").excludePathPatterns(list);
    }
}
