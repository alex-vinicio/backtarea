package pichincha.com.backtarea.Service;

import java.util.List;

import pichincha.com.backtarea.Entity.Cuenta;

public interface CuentaService {
    List<Cuenta> getAll();

    void eliminarCuentaPorId(Long idCuenta);

    Cuenta createCuenta(Cuenta cuenta);

    Cuenta updateCuenta(Long idCuenta, Cuenta cuenta);

    Cuenta getCuentaById(Long idCuenta);

}