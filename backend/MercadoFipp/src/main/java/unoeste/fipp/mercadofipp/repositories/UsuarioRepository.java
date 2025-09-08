package unoeste.fipp.mercadofipp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import unoeste.fipp.mercadofipp.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
