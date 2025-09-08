package unoeste.fipp.mercadofipp.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unoeste.fipp.mercadofipp.entities.Usuario;
import unoeste.fipp.mercadofipp.entities.Erro;
import unoeste.fipp.mercadofipp.services.UsuarioService;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("apis/usuario")
public class UsuarioRestController {
    @Autowired
    private UsuarioService usuarioService;
    @GetMapping
    public ResponseEntity<Object> getAll(){
        List<Usuario> usuarioList = usuarioService.getAll();
        if(usuarioList!=null && !usuarioList.isEmpty())
            return ResponseEntity.ok(usuarioList);
        return ResponseEntity.badRequest().body(new Erro("categorias não encontradas"));
    }
    @GetMapping("{id}")
    public ResponseEntity<Object> getId(@PathVariable int id){
        Usuario usuario = usuarioService.getId((long)id);
        if(usuario!=null)
            return ResponseEntity.ok(usuario);
        return ResponseEntity.badRequest().body(new Erro("usuario não encontradas"));
    }

    @GetMapping("teste/{nome}")
    public ResponseEntity<Object> getNome(@PathVariable(name = "nome") String nome){
        Usuario usuario = usuarioService.getNome(nome);
        if(usuario!=null)
            return ResponseEntity.ok(usuario);
        return ResponseEntity.badRequest().body(new Erro("usuario não encontradas"));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable int id){
        if(usuarioService.delete((long)id))
            return ResponseEntity.noContent().build();
        return ResponseEntity.badRequest().body(new Erro("Erro ao deletar o usuario"));
    }
    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Usuario usuario){
        Usuario novoUsuario=usuarioService.save(usuario);
        if(novoUsuario!=null)
            return ResponseEntity.ok(usuario);
        return ResponseEntity.badRequest().body("Erro ao cadastrar o usuario");
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody Usuario usuario){
        Usuario novoUsuario = usuarioService.add(usuario);
        if(novoUsuario != null)
            return ResponseEntity.ok(novoUsuario);
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao alterar o Usuario"));
    }
    // alterar
    //getId
    //apagar
}
