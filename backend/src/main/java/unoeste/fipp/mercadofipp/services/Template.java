package unoeste.fipp.mercadofipp.services;

import unoeste.fipp.mercadofipp.entities.Caixa;
import unoeste.fipp.mercadofipp.entities.Venda;

public abstract class Template {
    private Caixa caixa;

    public final boolean Gravar(Template t){

        if(caixa.isAberto()){
            if( t instanceof Venda)
            {
                //conecta
            }


        }

        return false;
    }
    protected abstract boolean gravarOb();
    private static boolean AtualizarEstoque(){

        return false;
    }

    protected abstract boolean GravarItens();



}
