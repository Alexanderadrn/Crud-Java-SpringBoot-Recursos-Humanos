package gm.rh.Controller;


import gm.rh.Exception.RecursoNoEncontradoExcepcion;
import gm.rh.Service.EmpleadoService;
import gm.rh.models.Empleado;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//http://localhost:8080/rh-app/
@RequestMapping("/rh-app")
@CrossOrigin(value = "http://localhost:3000")
public class EmpleadoController {
    private static final Logger logger =
            LoggerFactory.getLogger(EmpleadoController.class);
    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping("/empleados")
    public List<Empleado> obtenerEmpleados(){
       return this.empleadoService.listarEmpleados();
        /*var empleados=empleadoService.listarEmpleados();
        empleados.forEach((empleado -> logger.info(empleado.toString())));
        return empleados;*/
    }
    @PostMapping("/empleados")
    public Empleado agregarEmpleado(@RequestBody Empleado empleado){
        return this.empleadoService.guardarEmpleado(empleado);
    }
    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> obtenerEmpleadoPorId(@PathVariable Integer id){
        Empleado empleado = empleadoService.buscarEmpleadoPorId(id);
        if(empleado==null)
            throw new RecursoNoEncontradoExcepcion("No se encontro el id: " + id);
        return ResponseEntity.ok(empleado);
    }
    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarEmpleado(@PathVariable Integer id){
        Empleado empleado = empleadoService.buscarEmpleadoPorId(id);
        if(empleado==null)
            throw new RecursoNoEncontradoExcepcion("No se encontro el id: " + id);
        empleadoService.eliminarEmpleado(empleado);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

}
