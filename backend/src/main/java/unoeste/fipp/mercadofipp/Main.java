package unoeste.fipp.mercadofipp;

import unoeste.fipp.mercadofipp.entities.*;
import unoeste.fipp.mercadofipp.services.template.CompraService;
import unoeste.fipp.mercadofipp.services.template.Template;
import unoeste.fipp.mercadofipp.services.template.VendaService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        Categoria categoria = new Categoria(127L, "Carro veio");
        Categoria categoria2 = new Categoria(127L, "Comida");

        Usuario usr1 = new Usuario(127L, "Savoldi", "123", 3);
        Usuario usr2 = new Usuario(127L, "Gabriel", "1234", 3);
        Usuario usr3 = new Usuario(127L, "G_Savoldi", "12345", 3);
        Usuario usr4 = new Usuario(127L, "G_S", "123456", 3);

        LocalDate dataAtual = LocalDate.now();

        Anuncio anuncio1 = new Anuncio(127L, "Celta Vermelho", dataAtual, "Só a capa da gaita", 15000, 3, categoria, usr1);
        Anuncio anuncio2 = new Anuncio(127L, "Fuscão Preto", dataAtual, "Fuscão preto você é feito de aço Fez o meu peito em pedaço", 150000, 1, categoria, usr1);
        Anuncio anuncio3 = new Anuncio(127L, "Um saco de batata", dataAtual, "Tem algumas boas ainda nele da pra aproveitar", 25, 5, categoria2, usr1);



        List<Itens> list =  new ArrayList<>();
        Itens item1 = new Itens(null, 2, anuncio3, 2 * 25, null, null);
        Itens item2 = new Itens(null, 1, anuncio2, 150000, null, null);
        Itens item3 = new Itens(null, 1, anuncio3, 15000, null, null);


        anuncio3.vender(5,usr2);
        anuncio3.vender(10,usr3);
        anuncio3.vender(8,usr2);
        anuncio3.reabastecer(100); // reabastece e notifica os observer

        list.add(item1);
        list.add(item2);
        list.add(item3);


        Caixa caixa = new Caixa(true,0.50,0.25,null);
        Venda venda =  new Venda(99L,0.0,new Date(),list);
        venda.calcularTotal();
        Template novo = new VendaService(venda,caixa);
        novo.gravar();

        Compra compra =  new Compra(99L,0.0,new Date(),list);
        compra.calcularTotal();
        Template novo2 = new CompraService(compra,caixa);
        novo2.gravar();


    }
}


