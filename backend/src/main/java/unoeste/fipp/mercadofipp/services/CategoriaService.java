package unoeste.fipp.mercadofipp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unoeste.fipp.mercadofipp.entities.Anuncio;
import unoeste.fipp.mercadofipp.entities.Categoria;
import unoeste.fipp.mercadofipp.repositories.CategoriaRepository;

import java.util.List;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;
    public List<Categoria> getAll(){
        return categoriaRepository.findAll();
    }
    public Categoria save(Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    public Categoria getId(Long id) {
        return categoriaRepository.findById(id).get();
    }


    public Categoria add(Categoria categoria)
    {
        Categoria novaCategoria = categoriaRepository.save(categoria);

        return novaCategoria;
    }

    public boolean delete(long id) {
        try {
            categoriaRepository.delete(new Categoria(id, ""));
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    //inserir/alterar
    //getId
    //apagar
}
