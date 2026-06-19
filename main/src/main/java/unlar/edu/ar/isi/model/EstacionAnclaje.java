package unlar.edu.ar.isi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstacionAnclaje {
    private String nombre;
    private Map<String, Vehiculo> vehiculosDisponibles = new HashMap<>();

    public void agregarVehiculo(Vehiculo vehiculo) {
        vehiculosDisponibles.put(vehiculo.getPatente(), vehiculo);
    }

    public Vehiculo buscarVehiculo(String patente) {
        Vehiculo vehiculo = vehiculosDisponibles.get(patente);
        if (vehiculo == null) {
            throw new RuntimeException("Error");
        }
        return vehiculo;
    }

    public List<Vehiculo> obtenerTodos() {
        return new ArrayList<>(vehiculosDisponibles.values());
    }
}