package com.company.empleados.backend.controller;

import com.company.empleados.backend.model.Empleado;
import com.company.empleados.backend.services.IEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class EmpleadoController {

    @Autowired
    private IEmpleadoService empleadoService;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("list", empleadoService.buscar());
        return "index";
    }

    @PostMapping("/guardar")
    public String guardar(Empleado empleado, Model model) {
        empleadoService.guardar(empleado);
        return "redirect:/";
    }

    @GetMapping("/guardar/{id}")
    public String mostrarGuardar(@PathVariable String id, Model model) {
        if (id != null && !"0".equals(id)) {
            model.addAttribute("empleado", empleadoService.buscarPorId(id));
        } else {
            model.addAttribute("empleado", new Empleado());
        }
        return "guardar";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id, Model model) {
        empleadoService.eliminar(id);
        return "redirect:/";
    }

}
