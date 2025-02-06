package com.company.empleados.backend.dao;

import com.company.empleados.backend.model.Empleado;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmpleadoDao extends MongoRepository<Empleado, String> {
}
