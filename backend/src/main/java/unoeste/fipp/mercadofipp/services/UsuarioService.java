package unoeste.fipp.mercadofipp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import unoeste.fipp.mercadofipp.entities.Usuario;

import unoeste.fipp.mercadofipp.repositories.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getAll(){
        return usuarioRepository.findAll();
    }

    public Usuario save(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public Usuario getId(Long id) {
        return usuarioRepository.findById(id).get();
    }

    public Usuario getNome(String nome) {
        return usuarioRepository.getNome(nome);
    }

    public Usuario add(Usuario usuario)
    {
        Usuario novoUsuario = usuarioRepository.save(usuario);

        return novoUsuario;
    }

    public boolean delete(long id) {
        try {
            usuarioRepository.delete(new Usuario(id, "","",0));
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
