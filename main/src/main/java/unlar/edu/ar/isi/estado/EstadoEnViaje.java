package unlar.edu.ar.isi.estado;

import unlar.edu.ar.isi.model.Vehiculo;

public class EstadoEnViaje implements EstadoVehiculo {
    @Override
    public void desbloquear(Vehiculo vehiculo) {
        throw new RuntimeException("Error");
    }

    @Override
    public void finalizarViaje(Vehiculo vehiculo) {
        vehiculo.setEstado(new EstadoEnEspera());
    }

    @Override
    public void reparar(Vehiculo vehiculo) {
        throw new RuntimeException("Error");
    }

    @Override
    public String getNombre() {
        return "En Viaje";
    }
}