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

    public Template(Caixa caixa) {
        this.caixa = caixa;
    }

    public final boolean gravar(){
        System.out.println("\nTemplate chamado.");
        if (caixa != null && caixa.isAberto()) {

            if (this.gravarPrincipal()) {
                this.gravarItens();
                this.atualizarEstoque();
                this.movimentarCaixa();
                this.atualizarCaixa();
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
