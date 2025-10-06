package unoeste.fipp.mercadofipp;

import unoeste.fipp.mercadofipp.entities.*;

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

        Anuncio anuncio1 = new Anuncio(127L, "Celta Vermelho", dataAtual, "Só a capa da gaita", 15000, 1, categoria, usr1);
        Anuncio anuncio2 = new Anuncio(127L, "Fuscão Preto", dataAtual, "Fuscão preto você é feito de aço Fez o meu peito em pedaço", 150000, 1, categoria, usr1);
        Anuncio anuncio3 = new Anuncio(127L, "Um saco de batata", dataAtual, "Tem algumas boas ainda nele da pra aproveitar", 25, 5, categoria2, usr1);

        Itens item = new Itens(null, 5, anuncio3, 5 * 25, null, null);


        anuncio3.vender(5,usr2);
        anuncio3.vender(10,usr3);
        anuncio3.vender(8,usr2);
        anuncio3.reabastecer(100); // reabastece e notifica os observer



    }
}


