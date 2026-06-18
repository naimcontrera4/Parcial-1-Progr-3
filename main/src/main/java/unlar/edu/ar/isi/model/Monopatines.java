package unlar.edu.ar.isi.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Monopatines extends Vehiculo {
    private boolean amortiguacionReforzada;

    public Monopatines(String patente, double porcentajeBateria, double tarifaBase, boolean amortiguacionReforzada) {
        super(patente, porcentajeBateria, tarifaBase);
        this.amortiguacionReforzada = amortiguacionReforzada;
    }
}