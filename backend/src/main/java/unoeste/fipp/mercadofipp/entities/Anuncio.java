package unoeste.fipp.mercadofipp.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import unoeste.fipp.mercadofipp.services.obserever.Observers;
import unoeste.fipp.mercadofipp.services.obserever.Subjects;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "perguntas"})
public class Anuncio implements Subjects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "anu_id")
    private Long id;
    @Column(name = "anu_title")
    private String titulo;
    @Column(name = "anu_date")
    private LocalDate data;
    @Column(name = "anu_desc")
    private String descricao;
    @Column(name = "anu_price")
    private double preco;

    private int estoque;

    @ManyToOne
    @JoinColumn(name = "cat_id")
    private Categoria categoria;
    @ManyToOne
    @JoinColumn(name = "usr_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "anuncio")
    private List<Pergunta> perguntas;

    @OneToMany(mappedBy = "anuncio", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("anuncio") // ou use DTOs se preferir
    private List<Foto> fotos = new ArrayList<>();

    @Transient
    private  List<Observers> observers = new ArrayList<>();

    public Anuncio() {
        this(0L,"",null,"",0, 0,null, null);
    }

    public Anuncio(Long id, String titulo, LocalDate data, String descricao, double preco, int estoque, Categoria categoria, Usuario usuario) {
        this.id = id;
        this.titulo = titulo;
        this.data = data;
        this.descricao = descricao;
        this.preco = preco;
        this.estoque = estoque;
        this.categoria = categoria;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Pergunta> getPerguntas() {
        return perguntas;
    }

    public void setPerguntas(List<Pergunta> perguntas) {
        this.perguntas = perguntas;
    }

    public List<Foto> getFotos() {return fotos;}

    public void setFotos(List<Foto> fotos) {this.fotos = fotos;}

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public List<Observers> getObservers() {
        return observers;
    }

    public boolean vender(int qtd, Observers cliente){
        if(estoque>= qtd){
            estoque-=qtd;
            return true;
        }
        else{
            addObserver(cliente);
            return false;
        }
    }

    public void reabastecer(int qtd){
        this.estoque += qtd;
        notifyObservers();
    }

    @Override
    public void addObserver(Observers o){
        if(!observers.contains(o))
            observers.add(o);
    }



    @Override
    public void removerObserver(Observers o) {
        observers.remove(o);

    }


    @Override
    public void notifyObservers(){
        for(Observers o : observers){
            o.update(estoque,descricao);
        }
        observers.clear();
    }
}
