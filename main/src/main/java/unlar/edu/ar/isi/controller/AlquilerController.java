package unlar.edu.ar.isi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unlar.edu.ar.isi.dto.AlquilerResponse;
import unlar.edu.ar.isi.dto.DesbloqueoRequest;
import unlar.edu.ar.isi.dto.FinalizarRequest;
import unlar.edu.ar.isi.model.EstacionAnclaje;
import unlar.edu.ar.isi.model.Vehiculo;
import unlar.edu.ar.isi.tarifa.EstrategiaTarifa;
import unlar.edu.ar.isi.tarifa.TarifaEstandar;
import unlar.edu.ar.isi.tarifa.TarifaHoraPico;
import unlar.edu.ar.isi.tarifa.TarifaTemporalClimatico;
import unlar.edu.ar.isi.model.Monopatines;

import java.util.Map;

@RestController
@RequestMapping("/api/alquileres")
public class AlquilerController {

    private EstacionAnclaje estacion;

    public AlquilerController() {
        estacion = new EstacionAnclaje();
        estacion.setNombre("Estacion Central");
        estacion.agregarVehiculo(new Monopatines("MONO-123", 100, 200.0, true));
    }

    @PostMapping("/desbloquear")
    public ResponseEntity<?> desbloquearVehiculo(@RequestBody DesbloqueoRequest request) {
        try {
            Vehiculo vehiculo = estacion.buscarVehiculo(request.getPatenteVehiculo());
            vehiculo.getEstado().desbloquear(vehiculo);

            AlquilerResponse response = new AlquilerResponse(
                    vehiculo.getPatente(),
                    0.0,
                    0,
                    vehiculo.getEstado().getNombre()
            );
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
        }
    }

    @PostMapping("/finalizar")
    public ResponseEntity<?> finalizarVehiculo(@RequestBody FinalizarRequest request) {
        try {
            Vehiculo vehiculo = estacion.buscarVehiculo(request.getPatenteVehiculo());
            vehiculo.getEstado().finalizarViaje(vehiculo);

            EstrategiaTarifa estrategia;
            if ("HORA_PICO".equals(request.getCriterioTarifa())) {
                estrategia = new TarifaHoraPico();
            } else if ("TEMPORAL".equals(request.getCriterioTarifa())) {
                estrategia = new TarifaTemporalClimatico();
            } else {
                estrategia = new TarifaEstandar();
            }

            double costoFinal = estrategia.calcularCosto(vehiculo.getTarifaBase(), request.getMinutosTranscurridos());

            AlquilerResponse response = new AlquilerResponse(
                    vehiculo.getPatente(),
                    costoFinal,
                    request.getMinutosTranscurridos(),
                    vehiculo.getEstado().getNombre()
            );
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
        }
    }
}