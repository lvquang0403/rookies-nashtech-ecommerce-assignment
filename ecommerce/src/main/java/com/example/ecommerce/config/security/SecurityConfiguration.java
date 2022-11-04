package com.example.ecommerce.config.security;

import com.example.ecommerce.config.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/api/v1/product/**").permitAll()
                .mvcMatchers(HttpMethod.GET, "/api/v1/attribute/**").permitAll()
                .mvcMatchers(HttpMethod.GET, "/api/v1/category/**").permitAll()
                .mvcMatchers(HttpMethod.GET, "/api/v1/rating/**").permitAll()
                .antMatchers("/api/auth/**").permitAll()
//                .mvcMatchers(HttpMethod.POST,"/api/v1/cart/").permitAll()
//                .mvcMatchers(HttpMethod.GET, "api/v1/order").hasRole("ADMIN")
//                .mvcMatchers(HttpMethod.GET, "api/v1/order/*").hasAnyAuthority("USER","ADMIN")
//                .mvcMatchers(HttpMethod.PUT, "api/v1/order/*").hasRole("ADMIN")
//                .mvcMatchers(HttpMethod.POST, "api/v1/order" ).hasAnyAuthority("USER","ADMIN")
                .anyRequest().authenticated().
                and().logout()
                .invalidateHttpSession(true)
                .deleteCookies();






        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
        return authConfiguration.getAuthenticationManager();
    }




}
