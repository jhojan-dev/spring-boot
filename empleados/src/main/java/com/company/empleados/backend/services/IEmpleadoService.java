package com.company.empleados.backend.services;

import com.company.empleados.backend.model.Empleado;

import java.util.List;

public interface IEmpleadoService {

    List<Empleado> buscar();
    Empleado buscarPorId(String id);
    Empleado guardar(Empleado empleado);
    void eliminar(String id);

}
