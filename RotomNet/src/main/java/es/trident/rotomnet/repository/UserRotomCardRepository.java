package es.trident.rotomnet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.trident.rotomnet.model.RotomCard;
import es.trident.rotomnet.model.User;
import es.trident.rotomnet.model.UserRotomCard;
import es.trident.rotomnet.model.UserRotomCardId;

public interface UserRotomCardRepository extends JpaRepository<UserRotomCard,UserRotomCardId>{
	List<UserRotomCard> findByUser(User user);
	List<UserRotomCard> findByUserAndRotomCard(User user, RotomCard rotomCard);	
}