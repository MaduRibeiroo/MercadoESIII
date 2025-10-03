package unoeste.fipp.mercadofipp.services;

import org.springframework.beans.factory.annotation.Autowired;

import unoeste.fipp.mercadofipp.entities.Venda;
import unoeste.fipp.mercadofipp.repositories.VendaRepository;

import java.util.Date;
import java.util.List;

public class VendaService {
    @Autowired
    private VendaRepository vendaRepository;
    public List<Venda> getAll(){
        return vendaRepository.findAll();
    }
    public Venda save(Venda venda){
        return vendaRepository.save(venda);
    }

    public Venda getId(Long id) {
        return vendaRepository.findById(id).get();
    }


    public Venda add(Venda venda)
    {
        Venda novaVenda = vendaRepository.save(venda);
        return novaVenda;
    }

    public boolean delete(long id) {
        try {
            vendaRepository.delete(new Venda(id,null,null));
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

}
