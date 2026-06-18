package unlar.edu.ar.isi.dto;

import lombok.Data;

@Data
public class DesbloqueoRequest {
    private String idUsuario;
    private String patenteVehiculo;
    private String tipoPago;
}