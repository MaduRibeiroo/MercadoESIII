package unoeste.fipp.mercadofipp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import unoeste.fipp.mercadofipp.entities.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long> {

}
