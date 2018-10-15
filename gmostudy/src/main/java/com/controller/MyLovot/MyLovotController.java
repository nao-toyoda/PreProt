package com.controller.MyLovot;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@ComponentScan("com.controller")
public class MyLovotController {
	@RequestMapping("/MyLovot")
	//@ResponseBody
	public ModelAndView GmoMember() {
		ModelAndView mav = new ModelAndView();
        mav.setViewName("MyLOVOT/MyLOVOT");
        return mav;
	}
}
