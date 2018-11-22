package org.hanbo.boot.rest.config.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class AuthTokenSecurityProvider implements AuthenticationProvider
{
   @Override
   public Authentication authenticate(Authentication auth) throws AuthenticationException
   {
      if (auth == null)
      {
         return null;
      }
      
      String name = auth.getName();
      String password = "";
      if (auth.getCredentials() != null) 
      {
         password = auth.getCredentials().toString();
      }
      
      if (name == null || name.length() == 0)
      {
         return null;
      }

      if (password == null || password.length() == 0)
      {
         return null;
      }
      
      Authentication retVal = null;
      List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
      
      if (name.equalsIgnoreCase("anonymous-user") && password.equalsIgnoreCase("anonymouspass"))
      {
         grantedAuths.clear();
         retVal = new UsernamePasswordAuthenticationToken(
            "anonymous", "not-authenticated", grantedAuths
         );
      }
      else if (name.equalsIgnoreCase("Elrick") && password.equalsIgnoreCase("123tset321"))
      {
         grantedAuths.clear();
         grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
         retVal = new UsernamePasswordAuthenticationToken(
            name, "UserAuthenticated", grantedAuths
         );
      }

      System.out.println("Add Auth - User Name: " + retVal.getName());
      System.out.println("Add Auth - Roles Count: " + (retVal.getAuthorities() != null? retVal.getAuthorities().size() : 0));

      return retVal;
   }

   @Override
   public boolean supports(Class<?> tokenClass)
   {
      return tokenClass.equals(UsernamePasswordAuthenticationToken.class);
   }
}
