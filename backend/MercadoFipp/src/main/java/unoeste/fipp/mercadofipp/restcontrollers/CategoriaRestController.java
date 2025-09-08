package unoeste.fipp.mercadofipp.restcontrollers;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unoeste.fipp.mercadofipp.entities.Categoria;
import unoeste.fipp.mercadofipp.entities.Erro;
import unoeste.fipp.mercadofipp.services.CategoriaService;

import java.util.List;

@RestController
@RequestMapping("apis/categoria")
public class CategoriaRestController {
    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @GetMapping
    public ResponseEntity<Object> getCategorias(){
        List<Categoria> categoriaList = categoriaService.getAll();
        if(categoriaList!=null && !categoriaList.isEmpty())
            return ResponseEntity.ok(categoriaList);
        return ResponseEntity.badRequest().body(new Erro("categorias não encontradas"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCategoriaId(@PathVariable(name="id")Long id){
        Categoria categoria = categoriaService.getById(id);
        if(categoria==null)
            return ResponseEntity.badRequest().body(new Erro("Categoria não encontrada!"));
        else
            return ResponseEntity.ok(categoria);
    }

    @PostMapping
    public ResponseEntity<Object> addCategoria(@RequestBody Categoria categoria){
        Categoria novaCategoria=categoriaService.save(categoria);
        if(categoria==null)
            return ResponseEntity.badRequest().body("Erro ao cadastrar a categoria");
        else
            return ResponseEntity.ok(novaCategoria);
    }

    @PutMapping
    public ResponseEntity<Object> updateCategoria(@RequestBody Categoria categoria){
        Categoria novaCategoria=categoriaService.save(categoria);
        if(categoria==null)
            return ResponseEntity.badRequest().body("Erro ao cadastrar a categoria");
        else
            return ResponseEntity.ok(novaCategoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCategoria(@PathVariable Long id) {
        if (categoriaService.delete(id))
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao deletar categoria"));
    }
}
