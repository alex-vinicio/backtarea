package pichincha.com.backtarea.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pichincha.com.backtarea.Entity.Cuenta;
import pichincha.com.backtarea.Service.CuentaService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/bp")
public class CuentaController {
    // defino un atributo de tipo servicio cuenta, para asi acceder a los servicios
    // que definen la logica de negocio
    @Autowired
    private CuentaService cuentaService;

    @GetMapping("/cuenta")
    public ResponseEntity<List<Cuenta>> getAll() {
        List<Cuenta> list = cuentaService.getAll();
        return new ResponseEntity<List<Cuenta>>(list, HttpStatus.OK);
    }

    @GetMapping("/cuenta/{id}")
    public ResponseEntity<Cuenta> getCuentaById(@PathVariable("id") Long idCuenta) {
        Cuenta cuenta = cuentaService.getCuentaById(idCuenta);
        if (cuenta == null) {
            return new ResponseEntity<Cuenta>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Cuenta>(cuenta, HttpStatus.OK);
    }

    @PostMapping("/cuenta")
    public ResponseEntity<String> createCuenta(@RequestBody Cuenta cuenta) {
        Cuenta cuentaCreated = cuentaService.createCuenta(cuenta);
        if (cuentaCreated == null) {
            return new ResponseEntity<String>("Error con los datos ingresados!", HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<String>("cuenta Createdo", HttpStatus.CREATED);
        }
    }

    @PutMapping("/cuenta/{id}")
    public ResponseEntity<String> updateCuenta(@PathVariable("id") Long idCuenta, @RequestBody Cuenta cuenta) {
        Cuenta cuentaUpdated = cuentaService.updateCuenta(idCuenta, cuenta);
        if (cuentaUpdated == null) {
            return new ResponseEntity<String>("Error al actualizar", HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<String>("cuenta actualizado", HttpStatus.OK);
        }
    }

    @DeleteMapping("/cuenta/{id}")
    public HttpStatus deletegenUsuarioByIdUsuario(@PathVariable("id") Long idUsuario) {
        cuentaService.eliminarCuentaPorId(idUsuario);
        return HttpStatus.OK;
    }
}
