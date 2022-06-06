package pichincha.com.backtarea.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pichincha.com.backtarea.Repository.CuentaRepository;
import pichincha.com.backtarea.Entity.Cuenta;

@Service
public class CuentaServiceImp implements CuentaService {

    @Autowired
    CuentaRepository cuentaRepository;

    @Autowired
    UsuarioServiceImp usuarioService;

    public List<Cuenta> getAll() {
        List<Cuenta> listaCuentas = cuentaRepository.findAll();
        return listaCuentas;
    }

    public void eliminarCuentaPorId(Long idCuenta) {
        cuentaRepository.deleteById(idCuenta);
    }

    public Cuenta createCuenta(Cuenta cuenta) {
        if (usuarioService.getUsuarioById(cuenta.getUsuario().getIdUsuario()) == null) {
            return null;
        } else {
            try {
                Optional<Cuenta> cuentaNumCuentaRep = cuentaRepository.findByNumeroCuenta(cuenta.getNumeroCuenta());
                if (cuentaNumCuentaRep.isPresent()) {
                    return null;
                }

                cuenta.setFechaCreacionCuenta(new Date());
                Cuenta cuentaCreated = cuentaRepository.save(cuenta);
                return cuentaCreated;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

    }

    public Cuenta updateCuenta(Long idCuenta, Cuenta cuenta) {
        Optional<Cuenta> cuentaEncontrada = cuentaRepository.findById(idCuenta);

        // Si el usuario existe utilizando isPresent() actualizo sus datos en la BD
        if (cuentaEncontrada.isPresent()) {
            cuenta.setFechaCreacionCuenta(cuentaEncontrada.get().getFechaCreacionCuenta());
            cuenta.setIdCuenta(idCuenta);
            // guarda los datos actualizados en la BD utilizando JPA
            Cuenta cuentaUpdated = cuentaRepository.save(cuenta);
            return cuentaUpdated;
        } else {
            return null;
        }

    }

    public Cuenta getCuentaById(Long idCuenta) {
        Optional<Cuenta> cuentaEncontrada = cuentaRepository.findById(idCuenta);

        if (cuentaEncontrada.isPresent()) {
            return cuentaEncontrada.get();
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<Cuenta> getCuentasByUsuario(Long idUsuario) {
        // ArrayList<Cuenta> listaCuentas =
        // cuentaRepository.findByUsuarioIdUsuario(idUsuario);
        // return listaCuentas;
        return null;
    }
}
