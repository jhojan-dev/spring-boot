package com.company.empleados.backend.services.impl;

import com.company.empleados.backend.dao.IEmpleadoDao;
import com.company.empleados.backend.model.Empleado;
import com.company.empleados.backend.services.IEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService {

    @Autowired
    private IEmpleadoDao empleadoDao;

    @Override
    public List<Empleado> buscar() {
        return empleadoDao.findAll();
    }

    @Override
    public Empleado buscarPorId(String id) {
        Optional<Empleado> empleado = empleadoDao.findById(id);
        return empleado.orElse(null);
    }

    @Override
    public Empleado guardar(Empleado empleado) {
        return empleadoDao.save(empleado);
    }

    @Override
    public void eliminar(String id) {
        empleadoDao.deleteById(id);
    }
}
