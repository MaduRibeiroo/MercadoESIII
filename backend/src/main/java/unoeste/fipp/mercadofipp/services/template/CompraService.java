package unoeste.fipp.mercadofipp.services.template;

import org.springframework.beans.factory.annotation.Autowired;

import unoeste.fipp.mercadofipp.entities.Caixa;
import unoeste.fipp.mercadofipp.entities.Compra;
import unoeste.fipp.mercadofipp.entities.Itens;
import unoeste.fipp.mercadofipp.entities.Compra;
import unoeste.fipp.mercadofipp.repositories.CaixaRepository;
import unoeste.fipp.mercadofipp.repositories.CompraRepository;
import unoeste.fipp.mercadofipp.repositories.ItensRepository;
import unoeste.fipp.mercadofipp.repositories.CompraRepository;
import unoeste.fipp.mercadofipp.services.AnuncioService;

import java.util.List;

public class CompraService extends Template{
    @Autowired
    private CompraRepository compraRepository;
    @Autowired
    private CaixaRepository caixaRepository;
    @Autowired
    private ItensRepository itensRepository;
    private AnuncioService anuncioService;

    private Caixa caixa;
    private final Compra compra;

    public CompraService(Compra compra, Caixa caixa) {
        this.compra = compra;
        this.caixa = caixa;
    }

    @Override
    protected boolean gravarPrincipal() {
        try {
            compraRepository.save(compra);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected boolean atualizarEstoque() {
        for (Itens item : compra.getItens()) {
            anuncioService.atualizarEstoque(item.getProduto().getId(), item.getQtd());
        }
        return true;
    }

    @Override
    protected boolean gravarItens() {
        try {
            for (Itens item : compra.getItens()) {
                item.setTipo("compra");
                item.setTipoId(compra.getId());
                item.calcularTotal();
                itensRepository.save(item);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected boolean movimentarCaixa() {
        caixa.entrada(compra.getTotal());
        return true;
    }

    @Override
    protected boolean atualizarCaixa() {
        try {
            caixaRepository.save(caixa); // persiste o estado atualizado do caixa
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
