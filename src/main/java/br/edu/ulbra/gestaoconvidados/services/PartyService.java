package br.edu.ulbra.gestaoconvidados.services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.edu.ulbra.gestaoconvidados.entities.Party;
import br.edu.ulbra.gestaoconvidados.entities.User;
import br.edu.ulbra.gestaoconvidados.repositories.PartyRepository;

@Component
public class PartyService {

	@Autowired
	private PartyRepository partyRepository;
	
	public List<Party> getMyParties(){
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		User user = (User) session.getAttribute("user");
		List<Party> myParties = new ArrayList<Party>();
		if(user != null) {
			Iterable<Party> parties = partyRepository.findAll();
			for (Party party : parties) {
				if(user.getId().compareTo(party.getOwner().getId()) == 0) {
					myParties.add(party);
				}
			}
		}
		return myParties;
	}
}
