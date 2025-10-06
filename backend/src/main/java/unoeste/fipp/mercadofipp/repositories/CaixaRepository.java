package unoeste.fipp.mercadofipp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import unoeste.fipp.mercadofipp.entities.Caixa;

public interface CaixaRepository extends JpaRepository<Caixa, Long> {
}
