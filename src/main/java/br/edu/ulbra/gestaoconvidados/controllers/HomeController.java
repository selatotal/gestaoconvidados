package br.edu.ulbra.gestaoconvidados.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ulbra.gestaoconvidados.input.ForgotPasswordInput;
import br.edu.ulbra.gestaoconvidados.input.LoginInput;

@Controller
public class HomeController {

	@RequestMapping(value="/")
	public ModelAndView startPage(){
		return new ModelAndView("home/capa");
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(LoginInput loginInput){
		return "redirect:/home";
	}
	
	@RequestMapping(value="/logout")
	public String logout(){
		return "redirect:/";
	}
	
	@RequestMapping(value="/forgotPassword", method=RequestMethod.GET)
	public ModelAndView forgotPassword(){
		return new ModelAndView("home/forgotPassword");
	}

	@RequestMapping(value="/forgotPassword", method=RequestMethod.POST)
	public ModelAndView forgotPassword(ForgotPasswordInput forgotPasswordInput){
		return new ModelAndView("home/forgotPasswordResult");
	}
	
	@RequestMapping(value="/home")
	public ModelAndView home(){
		return new ModelAndView("home/home");
	}

}
