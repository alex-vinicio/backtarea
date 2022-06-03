package pichincha.com.backtarea.Service;

import java.util.List;

import pichincha.com.backtarea.Entity.Usuario;

public interface UsuarioService {

    List<Usuario> getAll();

    void eliminarUsuarioPorId(Long idUsuario);

    Usuario createUsuario(Usuario usuario);

    Usuario updateUsuario(Long idUsuario, Usuario usuario);

    Usuario getUsuarioById(Long idUsuario);

}
