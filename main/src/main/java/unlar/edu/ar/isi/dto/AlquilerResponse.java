package unlar.edu.ar.isi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AlquilerResponse {
    private String patente;
    private double costoFinalCalculado;
    private int tiempoTranscurrido;
    private String faseActual;
}