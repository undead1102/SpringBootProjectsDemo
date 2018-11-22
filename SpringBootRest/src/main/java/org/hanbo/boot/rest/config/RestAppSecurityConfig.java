package org.hanbo.boot.rest.config;

import org.hanbo.boot.rest.config.security.AuthTokenSecurityProvider;
import org.hanbo.boot.rest.config.security.RestAuthSecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class RestAppSecurityConfig  extends WebSecurityConfigurerAdapter
{
   @Autowired
   private AuthTokenSecurityProvider authProvider;
   
   @Override
   protected void configure(HttpSecurity http) throws Exception
   {
      System.out.println("    ++++++++++++ Called AuthTokenSecurityProvider.configure( Http Security )...");
      http.csrf().disable()
         .authorizeRequests()
         .antMatchers("/public/**").permitAll()
         .anyRequest().authenticated();
      http.addFilterAfter(new RestAuthSecurityFilter(super.authenticationManager()), BasicAuthenticationFilter.class);
   }
   
   @Override
   protected void configure(AuthenticationManagerBuilder authMgrBuilder)
      throws Exception
   {
      System.out.println("    ++++++++++++ Called AuthTokenSecurityProvider.configure( Auth Manager )...");
      authMgrBuilder.authenticationProvider(authProvider);
   }
   
   @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
   @Override
   public AuthenticationManager authenticationManagerBean() throws Exception {
       return super.authenticationManagerBean();
   }
}
