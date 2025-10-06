package unoeste.fipp.mercadofipp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import unoeste.fipp.mercadofipp.entities.Itens;

public interface ItensRepository extends JpaRepository<Itens, Long> {
}
