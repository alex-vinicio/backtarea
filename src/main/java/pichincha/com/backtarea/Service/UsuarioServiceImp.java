package pichincha.com.backtarea.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pichincha.com.backtarea.Entity.Usuario;
import pichincha.com.backtarea.Repository.UsuarioRepository;

@Service
public class UsuarioServiceImp implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public List<Usuario> getAll() {
        List<Usuario> listaUsuarios = usuarioRepository.findAll();
        return listaUsuarios;
    }

    public void eliminarUsuarioPorId(Long idUsuario) {
        try {
            usuarioRepository.deleteById(idUsuario);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Usuario createUsuario(Usuario usuario) {
        try {
            Optional<Usuario> usuarioRepetido = usuarioRepository.findByCiUsuario(usuario.getCiUsuario());

            if (usuarioRepetido.isPresent()) {
                return null;
            }
            usuario.setEdadUsuario();
            Usuario usuarioCreated = usuarioRepository.save(usuario);
            return usuarioCreated;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public Usuario updateUsuario(Long idUsuario, Usuario usuario) {
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findById(idUsuario);

        // Si el usuario existe utilizando isPresent() actualizo sus datos en la BD
        if (usuarioEncontrado.isPresent()) {
            usuario.setIdUsuario(idUsuario);// seteo el id del usuario a la entidad que llega, asi no crea una nueva
            usuario.setEdadUsuario();

            // guarda los datos actualizados en la BD utilizando JPA
            Usuario usuarioUpdated = usuarioRepository.save(usuario);
            return usuarioUpdated;
        } else {
            return null;
        }

    }

    public Usuario getUsuarioById(Long idUsuario) {
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findById(idUsuario);
        if (usuarioEncontrado.isPresent()) {
            return usuarioEncontrado.get();
        } else {
            return null;
        }
    }
}
