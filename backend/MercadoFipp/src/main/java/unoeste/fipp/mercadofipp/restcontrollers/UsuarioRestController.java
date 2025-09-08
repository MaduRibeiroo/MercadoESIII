package unoeste.fipp.mercadofipp.restcontrollers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.config.RepositoryNameSpaceHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ClientHttpResponseDecorator;
import org.springframework.web.bind.annotation.*;
import unoeste.fipp.mercadofipp.entities.Erro;
import unoeste.fipp.mercadofipp.entities.Usuario;
import unoeste.fipp.mercadofipp.services.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("apis/usuario")
public class UsuarioRestController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @GetMapping
    public ResponseEntity<Object> getUsuarios(){
        List<Usuario> usuarioList = usuarioService.getAll();
        if(usuarioList.isEmpty())
            return ResponseEntity.badRequest().body(new Erro("Não possui dados"));
        else
            return ResponseEntity.ok(usuarioList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUsuarioId(@PathVariable(name = "id") Long id){
        Usuario usuario = usuarioService.getById(id);
        if(usuario==null)
            return ResponseEntity.badRequest().body(new Erro("Usuario não encontrado"));
        else
            return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<Object> addUsuario(@RequestBody Usuario usuario){
        Usuario novoUsuario = usuarioService.save(usuario);
        if(novoUsuario==null)
            return ResponseEntity.badRequest().body(new Erro("Erro ao cadastrar o novo usuario"));
        else
            return ResponseEntity.ok(novoUsuario);
    }

    @PutMapping
    public ResponseEntity<Object> updateUsuario(@RequestBody Usuario usuario){
        Usuario novoUsuario = usuarioService.save(usuario);
        if(novoUsuario==null)
            return ResponseEntity.badRequest().body(new Erro("Erro ao alterar o novo usuario"));
        else
            return ResponseEntity.ok(novoUsuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUsuario(@PathVariable Long id){
        if(usuarioService.delete(id))
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao deletar usuario"));
    }

}
