package unoeste.fipp.mercadofipp.services.template;

import org.springframework.beans.factory.annotation.Autowired;

import unoeste.fipp.mercadofipp.entities.Caixa;
import unoeste.fipp.mercadofipp.entities.Itens;
import unoeste.fipp.mercadofipp.entities.Venda;
import unoeste.fipp.mercadofipp.repositories.CaixaRepository;
import unoeste.fipp.mercadofipp.repositories.ItensRepository;
import unoeste.fipp.mercadofipp.repositories.VendaRepository;
import unoeste.fipp.mercadofipp.services.AnuncioService;

import java.util.List;

public class VendaService extends Template{
    @Autowired
    private VendaRepository vendaRepository;
    @Autowired
    private CaixaRepository caixaRepository;
    @Autowired
    private ItensRepository itensRepository;
    private AnuncioService anuncioService;

    private Caixa caixa;
    private final Venda venda;

    public VendaService(Venda venda, Caixa caixa) {
        this.venda = venda;
        this.caixa = caixa;
    }

    @Override
    protected boolean gravarPrincipal() {
        try {
            vendaRepository.save(venda);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected boolean atualizarEstoque() {
        for (Itens item : venda.getItens()) {
            anuncioService.atualizarEstoque(item.getProduto().getId(), -item.getQtd());
        }
        return true;
    }

    @Override
    protected boolean gravarItens() {
        try {
            for (Itens item : venda.getItens()) {
                item.setTipo("VENDA");
                item.setTipoId(venda.getId());
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
        caixa.entrada(venda.getTotal());
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
