package unoeste.fipp.mercadofipp.entities;

import jakarta.persistence.*;

@Entity
public class Itens {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @Column(name="item_qtde")
    private int qtd;

    @ManyToOne
    @JoinColumn(name = "anu_id")
    private Anuncio produto;

    @Column(name = "item_total")
    private double total;

    @Column(name = "item_tipo")
    private String tipo;

    @Column(name = "item_tipoId")
    private Long tipoId;

    public Itens() {
    }

    public Itens(Long id, int qtd, Anuncio produto, double total, Long tipoId, String tipo) {
        this.id = id;
        this.qtd = qtd;
        this.produto = produto;
        this.total = total;
        this.tipoId = tipoId;
        this.tipo = tipo;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getTipoId() {
        return tipoId;
    }

    public void setTipoId(Long tipoId) {
        this.tipoId = tipoId;
    }

    public double calcularTotal(){
        this.total = qtd * produto.getPreco();
        return qtd * produto.getPreco();
    }
}
