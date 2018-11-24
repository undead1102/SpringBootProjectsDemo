package org.hanbo.boot.app.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SecuredPageController
{
   @PreAuthorize("hasRole('ROLE_ADMIN')")
   @RequestMapping(value="/secure/adminPage", method = RequestMethod.GET)
   public ModelAndView adminPage()
   {
        ModelAndView retVal = new ModelAndView();
        retVal.setViewName("webAccess");
        retVal.addObject("pageInfo", "The AWESOME Admin Page");
        retVal.addObject("userInfo", "Awesome Admin User.");
        return retVal;
   }
   
   @PreAuthorize("hasRole('ROLE_STAFF')")
   @RequestMapping(value="/secure/staffPage", method = RequestMethod.GET)
   public ModelAndView staffPage()
   {
        ModelAndView retVal = new ModelAndView();
        retVal.setViewName("webAccess");
        retVal.addObject("pageInfo", "The SUPPORTING Staff Page");
        retVal.addObject("userInfo", "T.L.C Staff User.");
        return retVal;
   }
   
   @PreAuthorize("hasRole('ROLE_USER')")
   @RequestMapping(value="/secure/userPage", method = RequestMethod.GET)
   public ModelAndView userPage()
   {
        ModelAndView retVal = new ModelAndView();
        retVal.setViewName("webAccess");
        retVal.addObject("pageInfo", "The LAMMO User Page");
        retVal.addObject("userInfo", "an ordinary User.");
        return retVal;
   }
}
