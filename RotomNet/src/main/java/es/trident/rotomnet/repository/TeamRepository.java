package es.trident.rotomnet.repository;



import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import es.trident.rotomnet.model.Team;
import es.trident.rotomnet.model.User;

public interface TeamRepository extends JpaRepository<Team,Integer>{
	Page<Team> findByUser(User user, Pageable page);
}
