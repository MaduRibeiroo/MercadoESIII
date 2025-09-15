package unoeste.fipp.mercadofipp.entities;

public class Itens {
    private int qtd;
    private Anuncio produto;
    private double total;

    public Itens() {
    }

    public Itens(int qtd, Anuncio produto, double total) {
        this.qtd = qtd;
        this.produto = produto;
        this.total = total;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public Anuncio getProduto() {
        return produto;
    }

    public void setProduto(Anuncio produto) {
        this.produto = produto;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
