package unoeste.fipp.mercadofipp.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import unoeste.fipp.mercadofipp.entities.Anuncio;
import unoeste.fipp.mercadofipp.entities.Erro;
import unoeste.fipp.mercadofipp.entities.Foto;
import unoeste.fipp.mercadofipp.repositories.AnuncioRepository;
import unoeste.fipp.mercadofipp.services.AnuncioService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("apis/anuncio")
public class AnuncioRestController {
    @Autowired
    private AnuncioService anuncioService;

    @Autowired
    private AnuncioRepository anuncioRepository;

    @GetMapping
    public ResponseEntity<List<Anuncio>> getAll() {
        List<Anuncio> anuncios = anuncioRepository.findAllComFotos();
        return ResponseEntity.ok(anuncios);  // Retorna OK com lista (mesmo que vazia)
    }


    @GetMapping("/listar-todos")
    public List<Anuncio> listarTodos() {
        return anuncioRepository.findAllComFotos();
    }


    @GetMapping("{id}")
    public ResponseEntity<Object> getId(@PathVariable int id) {
        Anuncio anuncios = anuncioService.getId((long)id);
        if(anuncios!=null)
            return ResponseEntity.ok(anuncios);
        else
            return ResponseEntity.badRequest().body(new Erro("Anuncios não encontrados"));
    }

    @GetMapping("get-anuncios/{titulo}")
    public ResponseEntity<Object> getTitulob(@PathVariable(name = "titulo") String titulo){
        List<Anuncio> anuncios = anuncioService.getTitulob(titulo);
        if(anuncios != null)
            return ResponseEntity.ok(anuncios);
        else
            return ResponseEntity.badRequest().body(new Erro("Anuncios não encontrados"));
    }

    @GetMapping("get-por-usuario/{id}")
    public ResponseEntity<Object> getIdUsu(@PathVariable(name = "id") Long id){
            List<Anuncio> anuncio = anuncioService.getIdUsu(id);
            if(anuncio != null)
                return ResponseEntity.ok(anuncio);
            else
                return ResponseEntity.badRequest().body(new Erro("Usuario não encontrado"));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable long id){
        if(anuncioService.delete(id))
            return ResponseEntity.noContent().build();
        return ResponseEntity.badRequest().body(new Erro("Erro ao deletar o anuncio"));
    }

    @DeleteMapping("pergunta/{id}")
    public ResponseEntity<Object> deletePergunta(@PathVariable long id){
        if(anuncioService.deletePergunta(id))
            return ResponseEntity.noContent().build();
        return ResponseEntity.badRequest().body(new Erro("Erro ao deletar o anuncio"));
    }

    @PostMapping("add-pergunta/{id}/{texto}")
    public ResponseEntity<Object> addPergunta(@PathVariable(name = "id") Long idAnuncio, @PathVariable(name = "texto") String texto){
         if(anuncioService.addPergunta(idAnuncio,texto))
             return ResponseEntity.noContent().build();
         else
             return ResponseEntity.badRequest().body(new Erro("erro ao adicionar a pergunta"));
    }

    @PostMapping("add-resposta/{idAnuncio}/{id}/{resposta}")
    public ResponseEntity<Object> addResposta(@PathVariable Long idAnuncio,@PathVariable Long id, @PathVariable String resposta) {
        try {
            anuncioService.addResposta(idAnuncio,id, resposta);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new Erro("Erro ao responder pergunta"));
        }
    }


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> addAnuncio(@RequestPart("anuncio") Anuncio anuncio, @RequestPart("fotos") MultipartFile[] fotos) {
        Anuncio novo = anuncioService.save(anuncio, fotos);
        if (novo != null)
            return ResponseEntity.ok(anuncio);
        return ResponseEntity.badRequest().body(new Erro("Erro ao cadastrar anúncio!"));
    }


    @PutMapping
    public ResponseEntity<Object> update(@RequestBody Anuncio anuncio){
        Anuncio novoAnuncio=anuncioService.add(anuncio);
        if(novoAnuncio!=null)
            return ResponseEntity.ok(novoAnuncio);
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao alterar o Anuncio"));
    }

    @PostMapping("comprar/{anuncioId}/{usuarioId")
    public ResponseEntity<Object> comprar(@PathVariable Long anuncioId, @PathVariable Long usuarioId){
        String resposta = anuncioService.comprarProduto(anuncioId,usuarioId);
        return ResponseEntity.ok(resposta);
    }

    @PostMapping("repor/{anuncioId}/{quantidade}")
    public ResponseEntity<Object> repor(@PathVariable Long anuncioId, @PathVariable int quantidade){
        String resposta = anuncioService.reporEstoque(anuncioId,quantidade);
        return ResponseEntity.ok(resposta);
    }


}
