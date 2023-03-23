package com.wipro.asg.vehicleregistration.security;

import com.wipro.asg.vehicleregistration.auth.UserPrincipalDetailsService;
import com.wipro.asg.vehicleregistration.utill.PasswordConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordConfig passwordConfig;
    private final UserPrincipalDetailsService userPrincipalDetailsService;
    private final ApplicationSuccessHandler successHandler;

    @Autowired
    public ApplicationSecurityConfig(PasswordConfig passwordConfig, UserPrincipalDetailsService userPrincipalDetailsService, ApplicationSuccessHandler successHandler) {
        this.passwordConfig = passwordConfig;
        this.userPrincipalDetailsService = userPrincipalDetailsService;
        this.successHandler = successHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/h2-console/**").permitAll()
                .antMatchers("/newRegistration", "/myRequests").hasRole("USER")
                .antMatchers("/adminHome").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("userID").passwordParameter("password").permitAll()
                .successHandler(successHandler)
                .and()
                .logout().logoutUrl("/logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/login");

        http.headers().frameOptions().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordConfig.passwordEncoder());
        provider.setUserDetailsService(userPrincipalDetailsService);
        return provider;
    }
}
