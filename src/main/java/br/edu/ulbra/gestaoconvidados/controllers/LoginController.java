package br.edu.ulbra.gestaoconvidados.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ulbra.gestaoconvidados.entities.User;
import br.edu.ulbra.gestaoconvidados.input.ForgotPasswordInput;
import br.edu.ulbra.gestaoconvidados.input.LoginInput;
import br.edu.ulbra.gestaoconvidados.repositories.UserRepository;

@Controller
public class LoginController {
	
	@Autowired
	UserRepository userRepository;

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView login(LoginInput loginInput){
		User user =  userRepository.findByEmail(loginInput.getUsername());
		if (user != null && user.getPassword().equals(loginInput.getPassword())){
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession();
			session.setAttribute("user", user);
			return new ModelAndView("/home/home");
		}
		ModelAndView mv = new ModelAndView("login/login");
		mv.addObject("loginError", true);
		return mv;
	}
	
	@RequestMapping(value="/logout")
	public ModelAndView logout(){
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		session.setAttribute("user", null);
		ModelAndView mv = new ModelAndView("/login/login");
		mv.addObject("logout", true);
		return mv;
	}
	
	@RequestMapping(value="/forgotPassword", method=RequestMethod.GET)
	public ModelAndView forgotPassword(){
		return new ModelAndView("home/forgotPassword");
	}
	
	@RequestMapping(value="/forgotPassword", method=RequestMethod.POST)
	public ModelAndView forgotPassword(ForgotPasswordInput forgotPasswordInput){
		ModelAndView mv = new ModelAndView("login/login");
		User user = userRepository.findByEmail(forgotPasswordInput.getUsername());
		if(user != null) {
			user.setPassword(forgotPasswordInput.getPassword());
			userRepository.save(user);
			mv.addObject("passwdChanged", true);
		} else {
			mv.addObject("mailNotFound", true);
		}
		return mv;
	}
}
