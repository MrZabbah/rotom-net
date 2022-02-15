package es.trident.rotomnet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.trident.rotomnet.model.UserRottomCard;
import es.trident.rotomnet.model.UserRottomCardId;

public interface UserRottomCardRepository extends JpaRepository<UserRottomCard,UserRottomCardId>{

}
