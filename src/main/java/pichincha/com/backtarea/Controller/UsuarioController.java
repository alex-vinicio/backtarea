package pichincha.com.backtarea.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import pichincha.com.backtarea.Entity.Usuario;
import pichincha.com.backtarea.Service.UsuarioService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/bp")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/usuario")
    public ResponseEntity<List<Usuario>> getAll() {
        List<Usuario> list = usuarioService.getAll();
        return new ResponseEntity<List<Usuario>>(list, HttpStatus.OK);
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable("id") Long idUsuario) {
        Usuario usuario = usuarioService.getUsuarioById(idUsuario);
        if (usuario == null) {
            return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }

    @PostMapping("/usuario")
    public ResponseEntity<String> createUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioCreated = usuarioService.createUsuario(usuario);
        if (usuarioCreated == null) {
            return new ResponseEntity<String>("Error con los datos ingresados!", HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<String>("usuario Createdo", HttpStatus.CREATED);
        }

    }

    @PutMapping("/usuario/{id}")
    public ResponseEntity<String> updateUsuario(@PathVariable("id") Long idUsuario, @RequestBody Usuario usuario) {
        Usuario usuarioUpdated = usuarioService.updateUsuario(idUsuario, usuario);
        if (usuarioUpdated == null) {
            return new ResponseEntity<String>("Error al actualizar", HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<String>("usuario actualizado", HttpStatus.OK);
        }
    }

    @DeleteMapping("/usuario/{id}")
    public HttpStatus deleteUsuarioById(@PathVariable("id") Long idUsuario) {
        usuarioService.eliminarUsuarioPorId(idUsuario);
        return HttpStatus.OK;
    }
}
