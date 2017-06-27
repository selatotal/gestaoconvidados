package br.edu.ulbra.gestaoconvidados.controllers;

import br.edu.ulbra.gestaoconvidados.entities.User;
import br.edu.ulbra.gestaoconvidados.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ulbra.gestaoconvidados.input.ForgotPasswordInput;
import br.edu.ulbra.gestaoconvidados.input.LoginInput;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	UserRepository userRepository;

	@RequestMapping(value="/")
	public ModelAndView startPage(){
		return new ModelAndView("home/capa");
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(LoginInput loginInput){
		User user =  userRepository.findByEmail(loginInput.getUsername());
		if (user != null && user.getPassword().equals(loginInput.getPassword())){
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession();
			session.setAttribute("user", user);
			return "redirect:/home";
		}
		return "redirect:/";
	}
	
	@RequestMapping(value="/logout")
	public String logout(){
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		session.setAttribute("user", null);
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
