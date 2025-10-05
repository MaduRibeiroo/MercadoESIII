package unoeste.fipp.mercadofipp.entities;



import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "venda")
public class Venda{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "venda_id")
    private Long id;

    @Column(name = "venda_total")
    private double total;


    @Column(name="venda_data")
    private Date data;

    @OneToMany(mappedBy = "venda")
    private List<Item> itens;



    public Venda() {this(0L,0, null,null);
    }

    public Venda(Long id, double total, Date data, List<Item> itens) {
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

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
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
           for(Item item: this.getItens()){
               to += item.getTotal();
           }
        }
        this.total = to;
        return to;
    }
}
