package unoeste.fipp.mercadofipp.entities;



import java.util.Date;
import java.util.List;

public class Venda extends Template{

    private Date data;

    private List<Itens> itens;

    public Venda() {
    }

    public Venda(Date data, List<Itens> itens) {
        this.data = data;
        this.itens = itens;
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
