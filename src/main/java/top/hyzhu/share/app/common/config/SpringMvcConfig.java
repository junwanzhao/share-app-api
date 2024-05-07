package top.hyzhu.share.app.common.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.hyzhu.share.app.common.interceptor.PermitResource;
import top.hyzhu.share.app.common.interceptor.TokenInterceptor;

/**
 * @Author: zhy
 * @Description: 用来处理跨域问题
 * @Date: 2024-04-28 20:35
 **/

@Configuration
@AllArgsConstructor
public class SpringMvcConfig implements WebMvcConfigurer {
    private final TokenInterceptor tokenInterceptor;
    private final PermitResource permitResource;
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedOriginPattern("*");
        corsConfiguration.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor)
//                添加需要被校验的路径
                .addPathPatterns(permitResource.getValidList());
    }
}
