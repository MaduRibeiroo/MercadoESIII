package unoeste.fipp.mercadofipp.entities;

public abstract class Template {
    private Caixa caixa;

    public final boolean Gravar(){

        if(caixa.isAberto()){

        }

        return false;
    }
    protected abstract boolean gravarOb();
    private static boolean AtualizarEstoque(){

        return false;
    }

    protected abstract boolean GravarItens();



}
