package edu.mum.cs.waa.project.bajargebeyaproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private DataSource dataSource;

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.
                jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS);

        http.authorizeRequests()
                .antMatchers("/webjars/**","/static/**","/error/**","/error?*","/user/register").permitAll()
                .antMatchers("/index").hasAnyRole("ADMIN","SELLER","BUYER")
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin()
                .loginPage("/user/login").failureUrl("/user/login?error=true")
                .usernameParameter("email").passwordParameter("password")
                .defaultSuccessUrl("/").permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                .logoutSuccessUrl("/user/login").and().exceptionHandling()
                .accessDeniedPage("/error/403")
        ;
    }

    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers("/resources/**","/uploads/**", "/templates/**", "/static/**", "/css/**", "/js/**", "/static/images/**", "/plugins/**", "/bootstrap-4.1.2/**");
    }



}
