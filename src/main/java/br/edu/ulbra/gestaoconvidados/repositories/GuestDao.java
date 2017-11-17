package br.edu.ulbra.gestaoconvidados.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.edu.ulbra.gestaoconvidados.entities.Guest;
import br.edu.ulbra.gestaoconvidados.entities.GuestPK;
import br.edu.ulbra.gestaoconvidados.entities.User;

@Repository
public class GuestDao {

	@Autowired
	private JdbcTemplate jdbcTemplateObject;

	public void deleteGuests(Long partyId, Long guestId) {
		String sql = "delete from party_guest where party_id = ? and guests_guest_id = ?";
		jdbcTemplateObject.update(sql, new Object[] { partyId, guestId });

		sql = "delete from user_guest where guests_guest_id = ?";
		jdbcTemplateObject.update(sql, new Object[] { guestId });

		sql = "delete from guest where guest_id = ? and party_id = ?";
		jdbcTemplateObject.update(sql, new Object[] { guestId, partyId });

		sql = "delete from user where id = ?";
		jdbcTemplateObject.update(sql, new Object[] { guestId });
	}
	
	public List<Guest> getGuestsByParty(Long partyId) {
		String sql = "select confirmed, email, name, id from user join guest on (user.id = guest.guest_id) where guest.party_id = ?";
		List<Guest> guestList = new ArrayList<Guest>();
		List<Map<String, Object>> rows = jdbcTemplateObject.queryForList(sql, new Object[]{partyId}) ;
		if ((rows != null && rows.size() > 0)) {
			for (Map<String, Object> tempRow : rows) {
				User user = new User();
				user.setName((String) tempRow.get("name")); 
				user.setEmail((String) tempRow.get("email"));
				user.setId((Long) tempRow.get("id"));
				GuestPK guestPK = new GuestPK();
				guestPK.setGuest(user);
				Guest guest = new Guest();
				guest.setConfirmed((Boolean) tempRow.get("confirmed"));
				guest.setId(guestPK);
				guestList.add(guest);
			}
		}
		return guestList;
	}
}
