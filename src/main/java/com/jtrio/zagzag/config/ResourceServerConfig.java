package com.jtrio.zagzag.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
@RequiredArgsConstructor
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers(HttpMethod.POST, "/users")
            .permitAll()
            .antMatchers(HttpMethod.GET, "/comments")
            .permitAll()
            .antMatchers(HttpMethod.GET, "/products")
            .permitAll()
            .antMatchers(HttpMethod.POST, "/products")
            .permitAll()
            .antMatchers(HttpMethod.GET, "/questions")
            .permitAll()
            .antMatchers(HttpMethod.GET, "/reviews")
            .permitAll()
            .anyRequest()
            .authenticated();
//                .anyRequest().permitAll();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("shop-api");
    }
}
