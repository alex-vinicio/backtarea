package pichincha.com.backtarea.Service;

import java.util.ArrayList;
import java.util.List;

import pichincha.com.backtarea.Entity.Cliente;
import pichincha.com.backtarea.Entity.Cuenta;
import pichincha.com.backtarea.Entity.Usuario;

public interface UsuarioService {

    List<Usuario> getAll();

    void eliminarUsuarioPorId(Long idUsuario);

    Usuario createUsuario(Usuario usuario);

    Usuario updateUsuario(Long idUsuario, Usuario usuario);

    Cliente<Cuenta> getUsuarioById(Long idUsuario);

}
