package unoeste.fipp.mercadofipp.services;

import org.springframework.beans.factory.annotation.Autowired;
import unoeste.fipp.mercadofipp.entities.Compra;
import unoeste.fipp.mercadofipp.repositories.CompraRepository;


import java.util.List;

public class CompraService extends Template{

    @Autowired
    private CompraRepository compraRepository;
    public List<Compra> getAll(){
        return compraRepository.findAll();
    }
    public Compra save(Compra venda){
        return compraRepository.save(venda);
    }

    public Compra getId(Long id) {
        return compraRepository.findById(id).get();
    }


    public Compra add(Compra compra)
    {
        Compra novaVenda = compraRepository.save(compra);
        return novaVenda;
    }

    public boolean delete(long id) {
        try {
            compraRepository.delete(new Compra(id,null,null));
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    protected boolean gravarOb() {
        return false;
    }

    @Override
    protected boolean GravarItens() {
        return false;
    }
}
