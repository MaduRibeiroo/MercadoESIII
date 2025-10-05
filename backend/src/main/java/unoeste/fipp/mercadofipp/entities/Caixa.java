package unoeste.fipp.mercadofipp.entities;

import java.util.ArrayList;
import java.util.List;

public class Caixa {
    private boolean aberto;
    private double saldoAtual;
    private double saldoInicial;
    private List<MovimentacaoCaixa> movimentacoes = new ArrayList<>();

    public Caixa(boolean aberto, double saldoAtual, double saldoInicial, List<MovimentacaoCaixa> movimentacoes) {
        this.aberto = aberto;
        this.saldoAtual = saldoAtual;
        this.saldoInicial = saldoInicial;
        this.movimentacoes = movimentacoes;
    }

    public boolean isAberto() {
        return aberto;
    }

    public void setAberto(boolean aberto) {
        this.aberto = aberto;
    }

    public double getSaldoAtual() {
        return saldoAtual;
    }

    public void setSaldoAtual(double saldoAtual) {
        this.saldoAtual = saldoAtual;
    }

    public double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public List<MovimentacaoCaixa> getMovimentacoes() {
        return movimentacoes;
    }

    public void setMovimentacoes(List<MovimentacaoCaixa> movimentacoes) {
        this.movimentacoes = movimentacoes;
    }

    public void entrada(double valor) {
        saldoAtual += valor;
        movimentacoes.add(new MovimentacaoCaixa("ENTRADA", valor));
    }

    public void saida(double valor) {
        saldoAtual -= valor;
        movimentacoes.add(new MovimentacaoCaixa("SAÍDA", valor));
    }
}
