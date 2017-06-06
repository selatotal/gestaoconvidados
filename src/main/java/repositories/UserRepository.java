package repositories;

import org.springframework.data.repository.CrudRepository;

import br.edu.ulbra.gestaoconvidados.entities.User;

public interface UserRepository extends CrudRepository<User, Long>{

	public User findByEmail(String email);
}
