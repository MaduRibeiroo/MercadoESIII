package unoeste.fipp.mercadofipp.services.template;

import org.springframework.beans.factory.annotation.Autowired;
import unoeste.fipp.mercadofipp.entities.Caixa;
import unoeste.fipp.mercadofipp.repositories.CompraRepository;
import unoeste.fipp.mercadofipp.repositories.VendaRepository;

public abstract class Template {
    protected Caixa caixa;
    @Autowired
    protected VendaRepository vendaRepository;
    @Autowired
    protected CompraRepository compraRepository;

    public final boolean gravar(){

        if (caixa != null && caixa.isAberto()) {
            if (gravarPrincipal()) {
                gravarItens();
                atualizarEstoque();
                movimentarCaixa();
                atualizarCaixa();
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
