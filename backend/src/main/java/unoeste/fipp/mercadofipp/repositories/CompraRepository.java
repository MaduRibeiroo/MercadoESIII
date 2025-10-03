package unoeste.fipp.mercadofipp.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import unoeste.fipp.mercadofipp.entities.Compra;

public interface CompraRepository extends JpaRepository<Compra, Long>{

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO compra () VALUES (:texto, :id_anuncio)", nativeQuery = true)

}
