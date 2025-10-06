package unoeste.fipp.mercadofipp.services;

import org.springframework.beans.factory.annotation.Autowired;
import unoeste.fipp.mercadofipp.entities.Anuncio;
import unoeste.fipp.mercadofipp.entities.Fornecedor;
import unoeste.fipp.mercadofipp.repositories.FornecedorRepository;
import unoeste.fipp.mercadofipp.services.obserever.ForncedorObserver;

import java.util.List;

public class FornecedorService {
    @Autowired
    private FornecedorRepository fornecedorRepository;

    public List<Fornecedor> getAll(){
        return fornecedorRepository.findAll();
    }

    public Fornecedor save(Fornecedor fornecedor){
        return fornecedorRepository.save(fornecedor);
    }

    public Fornecedor getId(Long id) {
        return fornecedorRepository.findById(id).get();
    }

    /*public Fornecedor getNome(String nome) {
        return fornecedorRepository.getNome(nome);
    }*/


    //.stream()
    //transforma essa lista em um fluxo de dados, permitindo aplicar operações em sequência, tipo um “pipeline funcional”.
    //.filter(f -> f.getNome().equalsIgnoreCase(nome))
    //filtra apenas os fornecedores cujo nome seja igual ao parâmetro nome, ignorando maiúsculas/minúsculas.
    //.findFirst()-pega o primeiro resultado encontrado (caso haja vários).
    public Fornecedor getNome(String nome) {
        return fornecedorRepository.findAll()
                .stream()
                .filter(f -> f.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);
    }



    public Fornecedor add(Fornecedor fornecedor)
    {
        Fornecedor novoFornecedor = fornecedorRepository.save(fornecedor);

        return novoFornecedor;
    }

    public boolean delete(long id) {
        try {
            fornecedorRepository.delete(new Fornecedor(id, "",""));
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    //coloca fornecedor como observador
    public void inscreverEmAnuncio(ForncedorObserver fornecedor, Anuncio anuncio){
        anuncio.addObserver(fornecedor);
    }

    //tira o fornecedor de obs
    public void removerInscricao(ForncedorObserver fornecedor, Anuncio anuncio){
        anuncio.removerObserver(fornecedor);
    }

}
