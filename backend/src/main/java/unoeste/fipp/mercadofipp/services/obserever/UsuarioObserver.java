package unoeste.fipp.mercadofipp.services.obserever;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import unoeste.fipp.mercadofipp.entities.Anuncio;
import unoeste.fipp.mercadofipp.entities.Usuario;
import unoeste.fipp.mercadofipp.repositories.UsuarioRepository;

import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UsuarioObserver implements Observers{

    private Usuario usuario;

    @Override
    public void update(int qtde, String descricao) {
        System.out.println("Cliente avisado de falta abastecimento de estoque");
    }
}
