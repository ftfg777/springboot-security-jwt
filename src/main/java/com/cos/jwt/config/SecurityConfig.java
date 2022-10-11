package com.cos.jwt.config;

import com.cos.jwt.filter.MyFilter1;
import com.cos.jwt.filter.MyFilter3;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.And;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig{

    private final CorsFilter corsFilter;


    // 스프링시큐리티가 우선 순위가 제일 높음 addFilterAfter로 해도 제일 높음
    // 스프링시큐리티 보다 먼저 동작하게 하고 싶다면 addFilterBefore에 등록
    // SecurityFilterChain에서 동작 순서를 보고 결정할 수 있음
    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(new MyFilter3(), UsernamePasswordAuthenticationFilter.class);
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션을 생성하지 않겠다
                .and()
                .addFilter(corsFilter) // CrossOrigin(인증이 없을 때 사용), 시큐리티 필터에 등록 인증(인증이 필요할 때)
                .formLogin().disable() // 폼태그 로그인 안 씀
                .httpBasic().disable() // http 로그인 방식 안 씀
                .authorizeRequests()
                .antMatchers("/api/v1/user/**")
                .access("hasRole('ROLE_USER') or HasRole('ROLE_MANAGER') or HasRole('ROLE_ADMIN')")
                .antMatchers("/api/v1/manager/**")
                .access("HasRole('ROLE_MANAGER') or HasRole('ROLE_ADMIN')")
                .antMatchers("/api/v1/admin/**")
                .access("HasRole('ROLE_ADMIN')")
                .anyRequest().permitAll();

        return http.build();


    }

}
