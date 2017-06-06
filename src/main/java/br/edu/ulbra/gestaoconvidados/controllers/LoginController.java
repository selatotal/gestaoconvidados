package br.edu.ulbra.gestaoconvidados.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	@RequestMapping(value={"/", "/home"})
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("login/home");
		return mv;
	}
	
	@RequestMapping("/hello")
	public ModelAndView hello() {
		ModelAndView mv = new ModelAndView("login/hello");
		return mv;
	}
	
	@RequestMapping("/login")
	public ModelAndView logout(){
		ModelAndView mv = new ModelAndView("login/login");
		return mv;
	}
}
