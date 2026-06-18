package unlar.edu.ar.isi.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BicicletaElectricas extends Vehiculo {
    private double capacidadCargaFrontalCm;

    public BicicletaElectricas(String patente, double porcentajeBateria, double tarifaBase, double capacidadCargaFrontalCm) {
        super(patente, porcentajeBateria, tarifaBase);
        this.capacidadCargaFrontalCm = capacidadCargaFrontalCm;
    }
}