package unlar.edu.ar.isi.model;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UsuarioPremium extends Usuario {
    private final double DESCUENTO = 0.15;

    public UsuarioPremium(String id, String nombreCompleto, double costoEstandarViaje) {
        super(id, nombreCompleto, costoEstandarViaje);
    }

    @Override
    public double calcularCostoFinal(Vehiculo vehiculo) {
        double costoTotal = this.getCostoEstandarViaje() + vehiculo.getTarifaBase();
        return costoTotal - (costoTotal * DESCUENTO);
    }
}