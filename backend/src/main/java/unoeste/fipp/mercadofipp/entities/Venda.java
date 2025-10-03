package unoeste.fipp.mercadofipp.entities;



import jakarta.persistence.*;
import unoeste.fipp.mercadofipp.services.Template;

import java.util.Date;
import java.util.List;

public class Venda extends Template {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "venda_id")
    private Long id;


    @Column(name="venda_data")
    private Date data;

    @OneToMany(mappedBy = "venda")
    private List<Itens> itens;

    public Venda() {this(0L,null,null);
    }

    public Venda(Long id, Date data, List<Itens> itens) {
        this.id = id;
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

    @Override
    protected boolean gravarOb() {
        return false;
    }

    @Override
    protected boolean GravarItens() {
        return false;
    }
}
