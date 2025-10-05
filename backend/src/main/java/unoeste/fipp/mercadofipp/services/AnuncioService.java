package unoeste.fipp.mercadofipp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import unoeste.fipp.mercadofipp.entities.Anuncio;
import unoeste.fipp.mercadofipp.entities.Pergunta;
import unoeste.fipp.mercadofipp.entities.Usuario;
import unoeste.fipp.mercadofipp.repositories.AnuncioRepository;
import unoeste.fipp.mercadofipp.entities.Foto;
import unoeste.fipp.mercadofipp.repositories.UsuarioRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class AnuncioService {
    @Autowired
    private AnuncioRepository anuncioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public AnuncioService(AnuncioRepository anuncioRepository, UsuarioRepository usuarioRepository) {
        this.anuncioRepository = anuncioRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Anuncio> getAll() {
        List<Anuncio> anuncios = anuncioRepository.findAll();
        for (Anuncio a : anuncios) {
            if (a.getFotos() == null) {
                a.setFotos(new ArrayList<>());
            }
        }
        return anuncios;
    }

    public Anuncio getId(long id) {
        return anuncioRepository.findById(id).orElse(null);
    }


    public Anuncio save(Anuncio anuncio, MultipartFile[] fotos) {
        System.out.println(">> Salvando anúncio: " + anuncio.getTitulo());
        System.out.println(">> Qtd de fotos recebidas: " + (fotos != null ? fotos.length : 0));
        Anuncio novoAnuncio = anuncioRepository.save(anuncio);
        if (novoAnuncio != null)
            addFoto(novoAnuncio.getId(), fotos);
        return novoAnuncio;
    }

    public Anuncio add(Anuncio anuncio)
    {
        Anuncio novoAnuncio=anuncioRepository.save(anuncio);
        //agora grave as fotos na tabela de fotos
        //for (Foto foto : anuncio.getFotos())
        //anuncioRepository.addFoto(foto.getFile(), novoAnuncio.getId());

        return novoAnuncio;
    }

    public boolean addPergunta(long id_anuncio, String texto){
        try{
            anuncioRepository.addPergunta(texto, id_anuncio);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public boolean deletePergunta(long id){
        try{
            anuncioRepository.deletePergunta(id);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    public boolean addResposta(long id_anuncio,long id ,String resposta){
        try{
            anuncioRepository.addResposta(resposta, id_anuncio,id);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public boolean addFoto(long idAnuncio, MultipartFile[] fotos) {
        try {
            for (MultipartFile foto : fotos) {
                System.out.println(">> Processando foto: " + foto.getOriginalFilename());
                byte[] bytes = foto.getBytes();
                String nomeArq = foto.getOriginalFilename();
                String extensao;
                int pos = nomeArq.lastIndexOf(".");
                extensao = nomeArq.substring(pos + 1);
                anuncioRepository.addFoto(bytes, idAnuncio, extensao);
                System.out.println(">> Foto inserida com sucesso.");
            }
            return true;
        } catch (Exception e) {
            System.err.println(">> Erro ao inserir foto: " + e.getMessage());
            return false;
        }
    }


    public List<Anuncio> getTitulob(String titulo) {
        titulo = titulo + "%";
        List<Anuncio> anuncios = anuncioRepository.getTitulob(titulo);
        for (Anuncio a : anuncios) {
            a.getFotos().size();
            a.getUsuario().getId();
            a.getCategoria().getId();
        }
        return anuncios;
    }


    public List<Anuncio> getIdUsu(long id){
        List<Anuncio> anuncios = anuncioRepository.getIdUsu(id);
        for (Anuncio a : anuncios) {
            a.getFotos().size();
            a.getUsuario().getId();
            a.getCategoria().getId();
        }
        return anuncios;
    }


    public boolean delete(long id){
        Anuncio anuncio=anuncioRepository.findById(id).orElse(null);
        try{
            anuncioRepository.deleteAllPer(id);
            anuncioRepository.deleteById(id);
            anuncioRepository.delFoto(anuncio.getId());
            return true;
        }catch (Exception e){
            return false;
        }

    }

    public String comprarProduto(Long anuncioId, Long usuarioId){
        Anuncio anuncio = anuncioRepository.findById(anuncioId)
                .orElseThrow(() -> new RuntimeException("Anúncio não encontrado"));

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        if(anuncio.getEstoque()>0){
            anuncio.setEstoque(anuncio.getEstoque() - 1);
            anuncioRepository.save(anuncio);
            return "Compra realizada com sucesso";
        }
        else{
            anuncio.addObserver(usuario);
            anuncioRepository.save(anuncio);
            return "Produto indisponível. Você sera notificado quando voltar ao estoque.";
        }
    }

    public String reporEstoque(Long anuncioId, int quantidade){
        if(quantidade<=0){
            throw new IllegalArgumentException("Quantidade deve ser positiva!");
        }
        Anuncio anuncio = anuncioRepository.findById(anuncioId)
                .orElseThrow(() -> new RuntimeException("Anúncio não encontrado"));

        anuncio.setEstoque(anuncio.getEstoque() + quantidade);
        anuncio.notifyObservers();
        anuncioRepository.save(anuncio);
        return "Estoque atualizado e clientes notificados";
    }

    public void atualizarEstoque(Long anuncioId, int quantidade) {
        Anuncio anuncio = anuncioRepository.findById(anuncioId)
                .orElseThrow(() -> new RuntimeException("Anúncio não encontrado"));

        // subtrai quantidade vendida do estoque
        anuncio.setEstoque(anuncio.getEstoque() + quantidade); // quantidade negativa se for venda
        anuncioRepository.save(anuncio);
    }

}
