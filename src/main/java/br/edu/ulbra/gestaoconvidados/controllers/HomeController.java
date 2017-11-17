package br.edu.ulbra.gestaoconvidados.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	@RequestMapping(value="/")
	public ModelAndView startPage(){
		if(isUserLoggedIn()) {
			return new ModelAndView("home/home");
		} else {
			return new ModelAndView("login/login");
		}
	}
	
	@RequestMapping(value="/home")
	public ModelAndView home(){
		return new ModelAndView("home/home");
	}
	
	private boolean isUserLoggedIn() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		return session.getAttribute("user") != null;
	}
}
