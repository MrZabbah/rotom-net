package es.trident.rotomnet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.trident.rotomnet.model.RotomCard;
import es.trident.rotomnet.model.User;
import es.trident.rotomnet.model.UserRotomCard;
import es.trident.rotomnet.model.util.UserRotomCardId;

public interface UserRotomCardRepository extends JpaRepository<UserRotomCard,UserRotomCardId>{
	List<UserRotomCard> findByUser(User user);
	int countByUser(User user);
	UserRotomCard findByUserAndRotomCard(User user, RotomCard rotomCard);	
	@Query(nativeQuery = true, value = "SELECT COUNT(*) FROM user_rotom_card as e WHERE e.user_user_id=?1 AND e.is_shiny_unlocked=1")
	int countByUserShiny(User user);
}