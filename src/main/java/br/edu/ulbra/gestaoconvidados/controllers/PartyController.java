package br.edu.ulbra.gestaoconvidados.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ulbra.gestaoconvidados.input.PartyInput;

@Controller
@RequestMapping("/party")
public class PartyController {

	@RequestMapping(value="/")
	public ModelAndView partyList(){
		return new ModelAndView("party/list");
	}
	
	@RequestMapping(value="/new", method=RequestMethod.GET)
	public ModelAndView newParty(){
		return new ModelAndView("party/new");
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public String newParty(PartyInput partyInput){
		return "redirect:/party";
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ModelAndView partyDetails(@PathVariable(name="id") Long id){
		return new ModelAndView("party/details");
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.POST)
	public String partyUpdate(@PathVariable(name="id") Long id, PartyInput partyInput){
		return "redirect:/party/"+id;
	}

	@RequestMapping(value="/party/{id}/delete")
	public String partyDelete(@PathVariable(name="id") Long id){
		return "redirect:/party/";
	}
	
	@RequestMapping(value="/party/{id}/guest/new", method=RequestMethod.POST)
	public String newGuest(@PathVariable(name="id") Long id){
		return "redirect:/party/" + id;
	}
	
	@RequestMapping(value="/party/{idParty}/guest/{idGuest}/delete", method=RequestMethod.POST)
	public String deleteGuest(@PathVariable(name="idParty") Long idParty, @PathVariable(name="idGuest") Long idGuest){
		return "redirect:/party/" + idParty;
	}
	
	
}
