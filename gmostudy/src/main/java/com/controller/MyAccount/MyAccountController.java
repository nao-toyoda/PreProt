package com.controller.MyAccount;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@ComponentScan("com.controller")
public class MyAccountController {
	@RequestMapping("/MyAccount")
	//@ResponseBody
	public ModelAndView GmoMember() {
		ModelAndView mav = new ModelAndView();
        mav.setViewName("MyAccount/MyAccount");
        return mav;
	}
}
