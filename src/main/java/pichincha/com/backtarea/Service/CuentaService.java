package pichincha.com.backtarea.Service;

import java.util.ArrayList;
import java.util.List;

import pichincha.com.backtarea.Entity.Cuenta;
import pichincha.com.backtarea.NullFoundException.CuentaServiceException;
import pichincha.com.backtarea.NullFoundException.CuentaServiceNullException;
import pichincha.com.backtarea.NullFoundException.CuentasServiceRootException;
import pichincha.com.backtarea.NullFoundException.UsuarioServiceException;

public interface CuentaService {
    // injection of dependencies
    List<Cuenta> getAll() throws CuentaServiceNullException;

    void eliminarCuentaPorId(Long idCuenta) throws CuentaServiceException;

    Cuenta createCuenta(Cuenta cuenta) throws CuentaServiceException, UsuarioServiceException;

    Cuenta updateCuenta(Long idCuenta, Cuenta cuenta);

    Cuenta getCuentaById(Long idCuenta) throws CuentasServiceRootException, CuentaServiceException;

    ArrayList<Cuenta> getCuentasByUsuario(Long idUsuario);

}
