package es.trident.rotomnet.service;



import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import es.trident.rotomnet.model.Team;

public interface TeamRepository extends JpaRepository<Team,Integer>{
	Page<Team> findAll(Pageable page);
}
