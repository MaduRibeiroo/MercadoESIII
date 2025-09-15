package unoeste.fipp.mercadofipp.entities;

public class Caixa {
    private boolean aberto;

    public Caixa(boolean aberto) {
        this.aberto = aberto;
    }

    public boolean isAberto() {
        return aberto;
    }

    public void setAberto(boolean aberto) {
        this.aberto = aberto;
    }
}
