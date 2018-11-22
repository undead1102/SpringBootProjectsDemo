package org.hanbo.boot.rest.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.hanbo.boot.rest.models.UserCredential;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class RestAuthSecurityFilter extends GenericFilterBean
{
   private AuthenticationManager authManager;
   
   public RestAuthSecurityFilter(AuthenticationManager authManager)
   {
      this.authManager = authManager;
   }
   
   @Override
   public void doFilter(
      ServletRequest request, 
      ServletResponse response,
      FilterChain chain) throws IOException, ServletException
   {
      try
      {
         if (request != null)
         {
            String auth = ((HttpServletRequest)request).getHeader("testAuth");
            System.out.println("Test Auth: " + auth);
            
            SecurityContext sc = SecurityContextHolder.getContext();
            UsernamePasswordAuthenticationToken authReq = null;
            
            authReq = getAuthCredentialFrom(auth);

            Authentication authCool = authManager.authenticate(authReq);
            sc.setAuthentication(authCool);
            chain.doFilter(request, response);
         }
         else
         {
            ((HttpServletResponse)response).sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Request.");
         }
      }
      catch (Exception ex)
      {
         throw new ServletException("Unknown exception in RestAuthSecurityFilter", ex);
      }
   }
   
   private UsernamePasswordAuthenticationToken getAuthCredentialFrom(String authHdrValue) throws JsonParseException, JsonMappingException, IOException
   {
      UsernamePasswordAuthenticationToken defUserToken =
         new UsernamePasswordAuthenticationToken("anonymous-user", "anonymouspass");
      if (authHdrValue == null || authHdrValue.length() == 0) 
      {
         return defUserToken;
      }
      else
      {
         byte[] decodedBytes = Base64.decodeBase64(authHdrValue);
         String authCred = new String(decodedBytes);
         System.out.println(authCred);
         if (authCred != null && authCred.length() > 0)
         {
            ObjectMapper objMapper = new ObjectMapper();
            UserCredential userCrede = objMapper.readValue(authCred, UserCredential.class);
            if (userCrede != null)
            {
               return new UsernamePasswordAuthenticationToken(userCrede.getUserName(), userCrede.getUserPassword());
            }
            else
            {
               return defUserToken;
            }
         }
         else
         {
            return defUserToken;
         }
      }
   }
}
