package unoeste.fipp.mercadofipp.services.template;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.beans.factory.annotation.Autowired;
import unoeste.fipp.mercadofipp.entities.Caixa;
import unoeste.fipp.mercadofipp.entities.Compra;
import unoeste.fipp.mercadofipp.entities.Itens;
import unoeste.fipp.mercadofipp.entities.Venda;
import unoeste.fipp.mercadofipp.repositories.AnuncioRepository;
import unoeste.fipp.mercadofipp.repositories.CompraRepository;
import unoeste.fipp.mercadofipp.repositories.VendaRepository;

public abstract class Template {
    protected Caixa caixa;

    public final boolean gravar(){
        System.out.println("\nTemplate chamado.");
        if (caixa != null && caixa.isAberto()) {
            if(this instanceof VendaService v){
                v.gravarPrincipal()) ;
                v.gravarItens();
                v.atualizarEstoque();
                v.movimentarCaixa();
                v.atualizarCaixa();
                return true;
            }
            else if(this instanceof CompraService c) {
                c.gravarPrincipal())
                c.gravarItens();
                c.atualizarEstoque();
                c.movimentarCaixa();
                c.atualizarCaixa();
                return true;
            }
        }
        return false;
    }

    protected abstract boolean gravarPrincipal();
    protected abstract boolean atualizarEstoque();
    protected abstract boolean gravarItens();
    protected abstract boolean movimentarCaixa();
    protected abstract boolean atualizarCaixa();



}
