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
        System.out.print("Compra gravada:\n Total da compra: "+compra.getTotal()+" Data: "+compra.getData());
        return true;
    }

    @Override
    protected boolean atualizarEstoque() {
        for (Itens item : compra.getItens()) {
            anuncioService.atualizarEstoque(item.getProduto().getId(), item.getQtd());
            System.out.println("\nAnuncio: "+item.getProduto().getTitulo()+" teve estoque atualizado.");
        }
        return true;
    }

    @Override
    protected boolean gravarItens() {
        for (Itens item : compra.getItens()) {
            item.setTipo("compra");
            item.setTipoId(compra.getId());
            item.calcularTotal();
            System.out.println("\nItem: "+item.getId()+" gravado com sucesso.");
        }
        return true;
    }

    @Override
    protected boolean movimentarCaixa() {
        System.out.println("\nNova saída no caixa de: "+compra.getTotal());
        caixa.entrada(compra.getTotal());
        return true;
    }

    @Override
    protected boolean atualizarCaixa() {
        System.out.println("\nCaixa atualizado. Novo valor: "+caixa.getSaldoAtual());
        return true;
    }
}
