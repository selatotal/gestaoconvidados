package br.edu.ulbra.gestaoconvidados.controllers;

import br.edu.ulbra.gestaoconvidados.entities.User;
import br.edu.ulbra.gestaoconvidados.repositories.UserRepository;
import br.edu.ulbra.gestaoconvidados.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ulbra.gestaoconvidados.input.UserInput;
import br.edu.ulbra.gestaoconvidados.repositories.ProfileRepository;

import javax.jws.WebParam;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
    private UserRepository userRepository;

	@Autowired
    private UserService userService;

	private ModelMapper mapper = new ModelMapper();

	@RequestMapping("/")
	public ModelAndView userList(){
		ModelAndView mv = new ModelAndView("user/list");
		mv.addObject("users", userRepository.findAll());
	    return mv;
	}
	
	@RequestMapping(value="/complimentaryData", method=RequestMethod.GET)
	public ModelAndView complimentaryData(){
		return new ModelAndView("user/complimentaryData");
	}
	
	@RequestMapping(value="/complimentaryData", method=RequestMethod.POST)
	public String complimentaryData(UserInput userInput){
		return "redirect:/home";
	}

	private ModelAndView userForm(UserInput userInput){
        ModelAndView mv = new ModelAndView("user/new");
        mv.addObject("user", userInput);
        mv.addObject("profiles", profileRepository.findAll());
        return mv;
    }

	@RequestMapping(value="/new", method=RequestMethod.GET)
	public ModelAndView newUser(){
		return this.userForm(new UserInput());
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public ModelAndView newUser(UserInput userInput){
        List<String> errors = userService.validateNew(userInput);
        if (errors.size() > 0){
            ModelAndView mv = this.userForm(userInput);
            mv.addObject("errors", errors);
            return mv;
        }

        User user = mapper.map(userInput, User.class);
        userRepository.save(user);
        userService.sendWelcomeEmail(user);
		return new ModelAndView("redirect:/user/?usercreated=true");
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ModelAndView userDetails(@PathVariable(name="id") Long id){
	    ModelAndView mv = new ModelAndView("user/details");
		User user = userRepository.findOne(id);
		if (user == null){
		    mv = new ModelAndView("redirect:/user/?usernotfound=true");
        } else {
		    UserInput userInput = mapper.map(user, UserInput.class);
		    mv.addObject("user", userInput);
		    mv.addObject("profiles", profileRepository.findAll());
        }
	    return mv;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.POST)
	public ModelAndView userUpdate(@PathVariable(name="id") Long id, UserInput userInput){
		List<String> errors = userService.validateUpdate(userInput);
        if (errors.size() > 0){
            ModelAndView mv = this.userForm(userInput);
            mv.setViewName("user/details");
            mv.addObject("errors", errors);
            return mv;
        }

        User user = mapper.map(userInput, User.class);
        userRepository.save(user);
        return new ModelAndView("redirect:/user/?userupdated=true");
	}

	@RequestMapping(value="/{id}/delete")
	public String userDelete(@PathVariable(name="id") Long id){
		User user = userRepository.findOne(id);
		if (user == null){
            return "redirect:/user/?usernotfound=true";
        }
        userRepository.delete(user);
	    return "redirect:/user/?userdeleted=true";
	}
}
