package br.edu.ulbra.gestaoconvidados.controllers;

import java.util.ArrayList;
import java.util.List;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ulbra.gestaoconvidados.entities.Guest;
import br.edu.ulbra.gestaoconvidados.entities.GuestPK;
import br.edu.ulbra.gestaoconvidados.entities.Party;
import br.edu.ulbra.gestaoconvidados.entities.User;
import br.edu.ulbra.gestaoconvidados.input.PartyInput;
import br.edu.ulbra.gestaoconvidados.input.UserInput;
import br.edu.ulbra.gestaoconvidados.repositories.GuestDao;
import br.edu.ulbra.gestaoconvidados.repositories.GuestRepository;
import br.edu.ulbra.gestaoconvidados.repositories.PartyRepository;
import br.edu.ulbra.gestaoconvidados.repositories.UserRepository;
import br.edu.ulbra.gestaoconvidados.services.PartyService;

@Controller
@RequestMapping("/party")
public class PartyController {

	@Autowired
	private PartyRepository partyRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private GuestRepository guestRepository;
	
	@Autowired
	private PartyService partyService;
	
	@Autowired
	private GuestDao guestDao;
	
	private ModelMapper mapper = new ModelMapper();

	@RequestMapping(value="/")
	public ModelAndView partyList(){
		ModelAndView mv = new ModelAndView("party/list");
		mv.addObject("party", new Party());
		mv.addObject("parties", partyRepository.findAll());
		mv.addObject("showLink", "allParties");
		return mv;
	}
	
	@RequestMapping(value="/myParties")
	public ModelAndView myPartyList(){
		ModelAndView mv = new ModelAndView("party/list");
		mv.addObject("party", new Party());
		mv.addObject("parties", partyService.getMyParties());		
		mv.addObject("showLink", "myParties");
		return mv;
	}
	
	@RequestMapping(value="/new", method=RequestMethod.GET)
	public ModelAndView newParty(){
		ModelAndView mv = new ModelAndView("party/new");
		mv.addObject("users", userRepository.findAll());
		mv.addObject("user", new User());
		mv.addObject("party", new Party());
		return mv;
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public String newParty(PartyInput partyInput){
		User user = userRepository.findOne(partyInput.getUserId());
		Party party = mapper.map(partyInput, Party.class);
		party.setOwner(user);
		partyRepository.save(party);
		return "redirect:/party/";
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ModelAndView partyDetails(@PathVariable(name="id") Long id){
		ModelAndView mv = new ModelAndView("party/details");
		Party party = partyRepository.findOne(id);
		mv.addObject("party", party);
		mv.addObject("guests", guestDao.getGuestsByParty(id));
		mv.addObject("user", new User());
		return mv;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.POST)
	public String partyUpdate(@PathVariable(name="id") Long id, PartyInput partyInput){
		Party party = mapper.map(partyInput, Party.class);
		User user = userRepository.findOne(partyInput.getUserId());
		party.setOwner(user);
		partyRepository.save(party);
		return "redirect:/party/"+id;
	}

	@RequestMapping(value="/party/{id}/delete")
	public String partyDelete(@PathVariable(name="id") Long id){
		partyRepository.delete(id);
		return "redirect:/party/";
	}
	
	@RequestMapping(value="/party/{id}/guest/new", method=RequestMethod.POST)
	public String newGuest(@PathVariable(name="id") Long partyId, UserInput guestInput){
		
		User user = mapper.map(guestInput, User.class);
		user.setId(null);
		user.setActive(Boolean.FALSE);
		userRepository.save(user);
		
		Party party = partyRepository.findOne(partyId);
		GuestPK guestPK = new GuestPK(user, party);
		Guest guest = new Guest(guestPK, guestInput.getActive());
		guestRepository.save(guest);
		
		List<Guest> guests = new ArrayList<>();
		guests.add(guest);
		user.setGuests(guests);
		userRepository.save(user);
		
		party.setGuests(guests);
		partyRepository.save(party);
		
		return "redirect:/party/" + partyId;
	}
	
	@RequestMapping(value="/party/{idParty}/guest/{idGuest}/delete", method=RequestMethod.GET)
	public String deleteGuest(@PathVariable(name="idParty") Long idParty, @PathVariable(name="idGuest") Long idGuest){
		/*
		Party party = partyRepository.findOne(idParty);
		GuestPK guestPK = new GuestPK(new User(idGuest), party);
		Guest guest = guestRepository.findOne(guestPK);
		guestRepository.delete(guest);
		*/
		
		guestDao.deleteGuests(idParty, idGuest);
		return "redirect:/party/" + idParty;
	}
}
