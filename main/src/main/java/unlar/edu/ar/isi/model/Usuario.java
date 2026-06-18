package unlar.edu.ar.isi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Usuario {
    private String id;
    private String nombreCompleto;
    private double costoEstandarViaje;

    public abstract double calcularCostoFinal(Vehiculo vehiculo);
}