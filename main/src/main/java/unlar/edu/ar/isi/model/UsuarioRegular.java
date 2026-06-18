package unlar.edu.ar.isi.model;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UsuarioRegular extends Usuario {

    public UsuarioRegular(String id, String nombreCompleto, double costoEstandarViaje) {
        super(id, nombreCompleto, costoEstandarViaje);
    }

    @Override
    public double calcularCostoFinal(Vehiculo vehiculo) {
        return this.getCostoEstandarViaje() + vehiculo.getTarifaBase();
    }
}