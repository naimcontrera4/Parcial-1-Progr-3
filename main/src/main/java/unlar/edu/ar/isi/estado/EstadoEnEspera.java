package unlar.edu.ar.isi.estado;

import unlar.edu.ar.isi.model.Vehiculo;

public class EstadoEnEspera implements EstadoVehiculo {
    @Override
    public void desbloquear(Vehiculo vehiculo) {
        vehiculo.setEstado(new EstadoEnViaje());
    }

    @Override
    public void finalizarViaje(Vehiculo vehiculo) {
        throw new RuntimeException("Error");
    }

    @Override
    public void reparar(Vehiculo vehiculo) {
        vehiculo.setEstado(new EstadoEnReparacion());
    }

    @Override
    public String getNombre() {
        return "En Espera";
    }
}