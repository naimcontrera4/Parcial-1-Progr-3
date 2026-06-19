package unlar.edu.ar.isi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unlar.edu.ar.isi.dto.VehiculoResponse;
import unlar.edu.ar.isi.model.EstacionAnclaje;
import unlar.edu.ar.isi.model.Vehiculo;
import unlar.edu.ar.isi.util.TarifaBaseComparator;
import unlar.edu.ar.isi.model.Monopatines;
import unlar.edu.ar.isi.model.BicicletaElectricas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    private EstacionAnclaje estacion;

    public VehiculoController() {
        estacion = new EstacionAnclaje();
        estacion.setNombre("Estacion Central");
        estacion.agregarVehiculo(new Monopatines("MONO-123", 100, 200.0, true));
        estacion.agregarVehiculo(new BicicletaElectricas("BICI-456", 10, 150.0, 30.0));
        estacion.agregarVehiculo(new Monopatines("MONO-999", 50, 250.0, false));
    }

    @GetMapping("/prioridad-carga")
    public ResponseEntity<List<VehiculoResponse>> getVehiculosPorBateria() {
        List<Vehiculo> lista = estacion.obtenerTodos();
        Collections.sort(lista);
        
        List<VehiculoResponse> respuesta = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            Vehiculo v = lista.get(i);
            respuesta.add(new VehiculoResponse(v.getPatente(), v.getPorcentajeBateria(), v.getTarifaBase(), v.getEstado().getNombre()));
        }
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/tarifa-descendente")
    public ResponseEntity<List<VehiculoResponse>> getVehiculosPorTarifa() {
        List<Vehiculo> lista = estacion.obtenerTodos();
        Collections.sort(lista, new TarifaBaseComparator());
        
        List<VehiculoResponse> respuesta = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            Vehiculo v = lista.get(i);
            respuesta.add(new VehiculoResponse(v.getPatente(), v.getPorcentajeBateria(), v.getTarifaBase(), v.getEstado().getNombre()));
        }
        return ResponseEntity.ok(respuesta);
    }
}