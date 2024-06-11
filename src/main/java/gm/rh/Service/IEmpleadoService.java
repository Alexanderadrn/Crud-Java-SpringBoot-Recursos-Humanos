package gm.rh.Service;

import gm.rh.models.Empleado;

import java.util.List;

public interface IEmpleadoService {
    public List<Empleado> listarEmpleados();

    public Empleado buscarEmpleadoPorId(Integer idEmpleado);

    public Empleado guardarEmpleado (Empleado  empleado);

    public  void eliminarEmpleado(Empleado empleado);
}
