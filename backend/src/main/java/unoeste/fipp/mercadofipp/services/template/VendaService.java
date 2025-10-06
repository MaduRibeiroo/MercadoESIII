package unoeste.fipp.mercadofipp.services.template;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import unoeste.fipp.mercadofipp.entities.Caixa;
import unoeste.fipp.mercadofipp.entities.Itens;
import unoeste.fipp.mercadofipp.entities.Venda;
import unoeste.fipp.mercadofipp.repositories.CaixaRepository;
import unoeste.fipp.mercadofipp.repositories.ItensRepository;
import unoeste.fipp.mercadofipp.repositories.VendaRepository;
import unoeste.fipp.mercadofipp.services.AnuncioService;

import java.util.List;


public class VendaService extends Template{

    private final Venda venda;

    public VendaService(Venda venda, Caixa caixa) {
        super(caixa);
        this.venda = venda;
    }

    @Override
    protected boolean gravarPrincipal() {
        System.out.print("Venda gravada:\n Total da compra: "+venda.getTotal()+" Data: "+venda.getData());
        return true;
    }

    @Override
    protected boolean atualizarEstoque() {
        for (Itens item : venda.getItens()) {
            System.out.println("\nAnuncio: "+item.getProduto().getTitulo()+" teve estoque atualizado.");
        }
        return true;
    }

    @Override
    protected boolean gravarItens() {
        for (Itens item : venda.getItens()) {
            item.setTipo("VENDA");
            item.setTipoId(venda.getId());
            item.calcularTotal();
            System.out.println("\nItem: "+item.getId()+" gravado com sucesso.");
        }
        return true;
    }

    @Override
    protected boolean movimentarCaixa() {
        System.out.println("\nNova entrada no caixa de: "+venda.getTotal());
        caixa.entrada(venda.getTotal());
        return true;
    }

    @Override
    protected boolean atualizarCaixa() {
        System.out.println("\nCaixa atualizado. Novo valor: "+caixa.getSaldoAtual());
        return true;
    }
}
