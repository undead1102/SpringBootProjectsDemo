package org.hanbo.boot.app.security;

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
public class UserAuthenticationService
implements AuthenticationProvider
{
   @Override
   public Authentication authenticate(Authentication auth) throws AuthenticationException
   {
      Authentication retVal = null;
      List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
      
      if (auth != null)
      {
         String name = auth.getName();
         String password = auth.getCredentials().toString();
         
         if (name.equals("admin") && password.equals("admin12345"))
         {
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_STAFF"));
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
            
            retVal = new UsernamePasswordAuthenticationToken(
               name, "", grantedAuths
            );
         }
         else if (name.equals("staff1") && password.equals("staff12345"))
         {
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_STAFF"));
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
            
            retVal = new UsernamePasswordAuthenticationToken(
               name, "", grantedAuths
            );
         }
         else if (name.equals("user1") && password.equals("user12345"))
         {
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
            
            retVal = new UsernamePasswordAuthenticationToken(
               name, "", grantedAuths
            );
         }
      }
      else
      {
         retVal = new UsernamePasswordAuthenticationToken(
            null, null, grantedAuths
         );
      }
      
      return retVal;
   }

   @Override
   public boolean supports(Class<?> tokenType)
   {
      return tokenType.equals(UsernamePasswordAuthenticationToken.class);
   }
}
