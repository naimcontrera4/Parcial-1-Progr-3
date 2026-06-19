package unlar.edu.ar.isi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VehiculoResponse {
    private String patente;
    private double porcentajeBateria;
    private double tarifaBase;
    private String faseActual;
}