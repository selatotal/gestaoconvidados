package br.edu.ulbra.gestaoconvidados.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ulbra.gestaoconvidados.entities.User;
import br.edu.ulbra.gestaoconvidados.input.UserInput;
import br.edu.ulbra.gestaoconvidados.repositories.ProfileRepository;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private ProfileRepository profileRepository;
	
	@RequestMapping("/")
	public ModelAndView userList(){
		return new ModelAndView("user/list");
	}
	
	@RequestMapping(value="/complimentaryData", method=RequestMethod.GET)
	public ModelAndView complimentaryData(){
		return new ModelAndView("user/complimentaryData");
	}
	
	@RequestMapping(value="/complimentaryData", method=RequestMethod.POST)
	public String complimentaryData(UserInput userInput){
		return "redirect:/home";
	}
	
	@RequestMapping(value="/new", method=RequestMethod.GET)
	public ModelAndView newUser(){
		ModelAndView mv = new ModelAndView("user/new");
		mv.addObject("user", new User());
		mv.addObject(profileRepository.findAll());
		return new ModelAndView("user/new");
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public String newUser(UserInput userInput){
		return "redirect:/user";
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ModelAndView userDetails(@PathVariable(name="id") Long id){
		return new ModelAndView("user/details");
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.POST)
	public String userUpdate(@PathVariable(name="id") Long id, UserInput userInput){
		return "redirect:/user/"+id;
	}

	@RequestMapping(value="/{id}/delete")
	public String userDelete(@PathVariable(name="id") Long id){
		return "redirect:/user/";
	}
}
