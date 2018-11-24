package org.hanbo.boot.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController
{
	@RequestMapping(value="/meow", method = RequestMethod.GET)
	public ModelAndView hello(
	   @RequestParam("sayit")
	   String sayit
	)
   {
		ModelAndView retVal = new ModelAndView();
		retVal.setViewName("testme");
		retVal.addObject("mymessage", sayit);
		return retVal;
	}
}