package unoeste.fipp.mercadofipp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import unoeste.fipp.mercadofipp.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
}
