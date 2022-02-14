package es.trident.rotomnet.service;

import org.springframework.data.jpa.repository.JpaRepository;
import es.trident.rotomnet.model.Team;

public interface TeamRepository extends JpaRepository<Team,Integer>{

}
