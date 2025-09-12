package unoeste.fipp.mercadofipp.entities;

import unoeste.fipp.mercadofipp.repositories.ObserverRepository;
import unoeste.fipp.mercadofipp.repositories.SujeitoRepository;

import java.util.ArrayList;
import java.util.List;

public class Estoque implements SujeitoRepository{
    private int quantidade;
    private String descricaoProduto;
    private final List<ObserverRepository> observers = new ArrayList<>();

    public Estoque(int quantidade, String descricaoProduto) {
        this.quantidade = quantidade;
        this.descricaoProduto = descricaoProduto;
    }

    public boolean vender(int qtd, ObserverRepository cliente){
        if(quantidade>= qtd){
            quantidade-=qtd;
            return true;
        }
        else{
            addObserver(cliente);
            return false;
        }
    }

    public void reabastecer(int qtd){
        this.quantidade += qtd;
        notifyObservers();
    }

    @Override
    public void addObserver(ObserverRepository o){
        if(!observers.contains(o))
            observers.add(o);
    }

    @Override
    public void removeObserver(ObserverRepository o){
        observers.remove(o);
    }

    @Override
    public void notifyObservers(){
        for(ObserverRepository o : observers){
            o.update(quantidade,descricaoProduto);
        }
        observers.clear();
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }
}
