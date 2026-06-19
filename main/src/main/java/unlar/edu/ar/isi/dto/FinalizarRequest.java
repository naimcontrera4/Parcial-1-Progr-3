package unlar.edu.ar.isi.dto;

import lombok.Data;

@Data
public class FinalizarRequest {
    private String patenteVehiculo;
    private int minutosTranscurridos;
    private String criterioTarifa; 
}