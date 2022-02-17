package es.trident.rotomnet.service;



import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import es.trident.rotomnet.model.Team_t;

public interface TeamRepository_t extends JpaRepository<Team_t,Integer>{
	Page<Team_t> findAll(Pageable page);
}
