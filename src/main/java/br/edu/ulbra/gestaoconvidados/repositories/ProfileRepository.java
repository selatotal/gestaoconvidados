package br.edu.ulbra.gestaoconvidados.repositories;

import org.springframework.data.repository.CrudRepository;

import br.edu.ulbra.gestaoconvidados.entities.Profile;

public interface ProfileRepository extends CrudRepository<Profile, Long>{

}
