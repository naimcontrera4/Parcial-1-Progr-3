package unlar.edu.ar.isi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Vehiculo {
    private String patente;
    private double porcentajeBateria;
    private double tarifaBase;
}