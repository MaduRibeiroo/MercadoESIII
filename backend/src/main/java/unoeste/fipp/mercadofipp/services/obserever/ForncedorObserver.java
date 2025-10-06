package unoeste.fipp.mercadofipp.services.obserever;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import unoeste.fipp.mercadofipp.entities.Anuncio;
import unoeste.fipp.mercadofipp.entities.Fornecedor;
import unoeste.fipp.mercadofipp.repositories.FornecedorRepository;

import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ForncedorObserver implements Observers {

    private Fornecedor fornecedor;

    @Override
    public void update(int qtde, String descricao) {
        System.out.println("Fornecedor avisado de falta d eestoque");
    }
}
