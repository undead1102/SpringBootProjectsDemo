package org.hanbo.boot.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController
{
   @RequestMapping(value="/login", method = RequestMethod.GET)
   public ModelAndView login()
   {
        ModelAndView retVal = new ModelAndView();
        retVal.setViewName("login");
        return retVal;
   }
   
   @RequestMapping(value="/public/logout", method = RequestMethod.GET)
   public ModelAndView logout()
   {
        ModelAndView retVal = new ModelAndView();
        retVal.setViewName("logout");
        return retVal;
   }
   
   @RequestMapping(value="/public/authFailed", method = RequestMethod.GET)
   public ModelAndView authFailed()
   {
        ModelAndView retVal = new ModelAndView();
        retVal.setViewName("authFailed");
        return retVal;
   }
   
   @RequestMapping(value="/public/accessDenied", method = RequestMethod.GET)
   public ModelAndView accessDenied()
   {
        ModelAndView retVal = new ModelAndView();
        retVal.setViewName("accessDenied");
        return retVal;
   }
}
