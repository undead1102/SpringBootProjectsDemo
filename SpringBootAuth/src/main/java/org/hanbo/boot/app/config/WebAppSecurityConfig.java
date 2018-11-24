package org.hanbo.boot.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.hanbo.boot.app.security.UserAuthenticationService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter
{
   @Autowired
   private UserAuthenticationService authenticationProvider;
   
   @Override
   protected void configure(HttpSecurity http) throws Exception
   {
      http.authorizeRequests()
         .antMatchers("/public/**", "/assets/**").permitAll()
         .anyRequest().authenticated()
         .and()
      .formLogin()
         .loginPage("/login")
         .permitAll()
         .usernameParameter("username")
         .passwordParameter("password")
         .defaultSuccessUrl("/secure/index", true).failureUrl("/public/authFailed")
         .successHandler(new SavedRequestAwareAuthenticationSuccessHandler())
         .and()
         .exceptionHandling().accessDeniedPage("/public/accessDenied")
         .and()
      .logout()
         .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
         .logoutSuccessUrl("/public/logout").permitAll();
   }
   
   @Override
   protected void configure(AuthenticationManagerBuilder authMgrBuilder)
      throws Exception
   {
      authMgrBuilder.authenticationProvider(authenticationProvider);
   }
}
