package repositories;

import org.springframework.data.repository.CrudRepository;

import br.edu.ulbra.gestaoconvidados.entities.Party;

public interface PartyRepository extends CrudRepository<Party, Long>{

}
