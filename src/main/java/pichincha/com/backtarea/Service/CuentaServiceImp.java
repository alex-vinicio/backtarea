package pichincha.com.backtarea.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pichincha.com.backtarea.Repository.CuentaRepository;
import pichincha.com.backtarea.Entity.Cuenta;
import pichincha.com.backtarea.NullFoundException.CuentaServiceException;
import pichincha.com.backtarea.NullFoundException.CuentaServiceNullException;
import pichincha.com.backtarea.NullFoundException.UsuarioServiceException;

@Service
public class CuentaServiceImp implements CuentaService {

    @Autowired
    CuentaRepository cuentaRepository;

    @Autowired
    UsuarioServiceImp usuarioService;

    public List<Cuenta> getAll() throws CuentaServiceNullException {
        try {
            List<Cuenta> listaCuentas = cuentaRepository.findAll();
            if (!listaCuentas.isEmpty()) {
                return listaCuentas;
            } else {
                throw new CuentaServiceNullException("No se encontraron cuentas");
            }

        } catch (CuentaServiceNullException e) {
            e.printStackTrace();
            throw new CuentaServiceNullException("Error, la lista esta vacia!");
        }

    }

    public void eliminarCuentaPorId(Long idCuenta) throws CuentaServiceException {
        try {
            cuentaRepository.deleteById(idCuenta);
        } catch (Exception e) {
            // e.printStackTrace();
            throw new CuentaServiceException("Error al eliminar la cuenta por id:" + idCuenta + "!");
        }
    }

    public Cuenta createCuenta(Cuenta cuenta) throws CuentaServiceException, UsuarioServiceException {
        try {
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
                    e.getMessage();
                    throw new CuentaServiceException("Problemas al setear los datos a la tabla Cuenta!");
                }
            }
        } catch (CuentaServiceNullException e) {
            e.printStackTrace();
            throw new CuentaServiceNullException("Error, al crear la cuenta!");
        }
    }

    public Cuenta updateCuenta(Long idCuenta, Cuenta cuenta) {
        try {
            Optional<Cuenta> cuentaEncontrada = cuentaRepository.findById(idCuenta);
            // Si el usuario existe utilizando isPresent() actualizo sus datos en la BD
            if (cuentaEncontrada.isPresent()) {
                cuenta.setFechaCreacionCuenta(cuentaEncontrada.get().getFechaCreacionCuenta());
                cuenta.setIdCuenta(idCuenta);
                // guarda los datos actualizados en la BD utilizando JPA
                Cuenta cuentaUpdated = cuentaRepository.save(cuenta);
                return cuentaUpdated;
            } else {
                throw new CuentaServiceNullException(
                        "Error, la Cuenta " + cuenta.getNumeroCuenta() + " esta vacia o envia mal los parametros!");
            }
        } catch (CuentaServiceNullException e) {
            e.printStackTrace();
            throw new CuentaServiceNullException("Error, al actualizar la " + cuenta.getNumeroCuenta() + "!");
        }
    }

    public Cuenta getCuentaById(Long idCuenta) throws CuentaServiceException {
        try {
            Optional<Cuenta> cuentaEncontrada = cuentaRepository.findById(idCuenta);

            if (cuentaEncontrada.isPresent()) {
                return cuentaEncontrada.get();
            } else {
                throw new CuentaServiceException("Error, la Cuenta " + idCuenta + " no existe!");
            }
        } catch (CuentaServiceNullException e) {
            e.printStackTrace();
            throw new CuentaServiceNullException("Error, la Cuenta " + idCuenta + " esta vacia!");
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
