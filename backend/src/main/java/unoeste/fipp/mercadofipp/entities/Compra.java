package unoeste.fipp.mercadofipp.entities;

import jakarta.persistence.*;
import unoeste.fipp.mercadofipp.services.template.Template;

import java.util.Date;
import java.util.List;

public  class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "compra_id")
    private Long id;

    @Column(name = "compra_total")
    private double total;

    @Column(name="compra_data")
    private Date data;

    @OneToMany(mappedBy = "compra")
    private List<Itens> itens;

    public Compra() {this(0L,0,null,null);
    }

    public Compra(Long id, double total, Date data, List<Itens> itens) {
        this.id = id;
        this.total = total;
        this.data = data;
        this.itens = itens;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public List<Itens> getItens() {
        return itens;
    }

    public void setItens(List<Itens> itens) {
        this.itens = itens;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double calcularTotal() {
        double to = 0;
        if (itens != null || !itens.isEmpty()) {
            for(Itens item: this.getItens()){
                to += item.getTotal();
            }
        }
        this.total = to;
        return to;
    }
}
