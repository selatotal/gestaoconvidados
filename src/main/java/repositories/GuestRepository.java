package repositories;

import org.springframework.data.repository.CrudRepository;

import br.edu.ulbra.gestaoconvidados.entities.Guest;
import br.edu.ulbra.gestaoconvidados.entities.GuestPK;

public interface GuestRepository extends CrudRepository<Guest, GuestPK>{

}
