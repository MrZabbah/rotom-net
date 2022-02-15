package es.trident.rotomnet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.trident.rotomnet.model.Pokemon;
import es.trident.rotomnet.model.RottomCard;

public interface RottomCardRepository extends JpaRepository<RottomCard, Pokemon>{

}
