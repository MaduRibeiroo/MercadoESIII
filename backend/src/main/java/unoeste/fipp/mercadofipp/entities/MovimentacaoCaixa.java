package unoeste.fipp.mercadofipp.entities;

import java.time.LocalDateTime;

public class MovimentacaoCaixa {
    private String tipo; // "ENTRADA" ou "SAÍDA"
    private double valor;
    private LocalDateTime data;

    public MovimentacaoCaixa(String tipo, double valor) {
        this.tipo = tipo;
        this.valor = valor;
        this.data = LocalDateTime.now();
    }

    public String getTipo() {
        return tipo;
    }

    public double getValor() {
        return valor;
    }

    public LocalDateTime getData() {
        return data;
    }
}
