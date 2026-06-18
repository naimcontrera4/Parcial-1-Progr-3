package unlar.edu.ar.isi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unlar.edu.ar.isi.dto.DesbloqueoRequest;
import unlar.edu.ar.isi.exceptions.BateriaInsuficienteException;
import unlar.edu.ar.isi.model.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/alquileres")
public class AlquilerController {

    private EstacionAnclaje estacion;
    private Map<String, Usuario> usuariosDb;
    private ProcesamientoDePagos procesadorPagos;

    public AlquilerController() {
        procesadorPagos = new ProcesamientoDePagos();
        estacion = new EstacionAnclaje();
        estacion.setNombre("Estacion Central");
        
        estacion.agregarVehiculo(new Monopatines("MONO-123", 100, 200.0, true));
        estacion.agregarVehiculo(new BicicletaElectricas("BICI-456", 10, 150.0, 30.0));
        estacion.agregarVehiculo(new Monopatines("MONO-999", 50, 200.0, false));

        usuariosDb = new HashMap<>();
        usuariosDb.put("U1", new UsuarioRegular("U1", "Juan Perez", 50.0));
        usuariosDb.put("U2", new UsuarioPremium("U2", "Naim Contrera", 50.0));
    }

    @GetMapping("/desbloquear")
    public ResponseEntity<?> desbloquearVehiculo(@RequestBody DesbloqueoRequest request) {
        try {
            Usuario usuario = usuariosDb.get(request.getIdUsuario());
            if (usuario == null) {
                return ResponseEntity.badRequest().body("Usuario no encontrado.");
            }

            Vehiculo vehiculo = estacion.buscarVehiculo(request.getPatenteVehiculo());

            if (vehiculo.getPorcentajeBateria() <= 15) {
                System.out.println("Alarma: Batería Insuficiente");
                throw new BateriaInsuficienteException("Batería Insuficiente. Nivel actual: " + vehiculo.getPorcentajeBateria() + "%");
            }

            double importeFinal = usuario.calcularCostoFinal(vehiculo);

            MetodoPago metodoPago = request.getTipoPago().equalsIgnoreCase("TARJETA") 
                    ? new TarjetaDeCredito() 
                    : new BilleteraVirtual();

            procesadorPagos.cobrar(metodoPago, importeFinal);

            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Vehículo desbloqueado exitosamente.");
            response.put("vehiculo", vehiculo);
            response.put("montoCobrado", importeFinal);

            return ResponseEntity.ok(response);

        } catch (BateriaInsuficienteException ex) {
            
            return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
        } catch (RuntimeException ex) {
            
            return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
        }
    }
}