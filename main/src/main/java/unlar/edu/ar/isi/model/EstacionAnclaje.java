package unlar.edu.ar.isi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstacionAnclaje {
    private String nombre;
    private List<Vehiculo> vehiculosDisponibles = new ArrayList<>();

    public void agregarVehiculo(Vehiculo vehiculo) {
        vehiculosDisponibles.add(vehiculo);
    }

    public Vehiculo buscarVehiculo(String patente) {
        return vehiculosDisponibles.stream()
                .filter(v -> v.getPatente().equalsIgnoreCase(patente))
                .findFirst()
                .orElseThrow(() -> {
                    System.out.println("Vehículo No Encontrado");
                    return new RuntimeException("Vehículo No Encontrado");
                });
    }
}