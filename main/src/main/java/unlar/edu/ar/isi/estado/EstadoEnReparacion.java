package unlar.edu.ar.isi.estado;

import unlar.edu.ar.isi.model.Vehiculo;

public class EstadoEnReparacion implements EstadoVehiculo {
    @Override
    public void desbloquear(Vehiculo vehiculo) {
        throw new RuntimeException("Error");
    }

    @Override
    public void finalizarViaje(Vehiculo vehiculo) {
        throw new RuntimeException("Error");
    }

    @Override
    public void reparar(Vehiculo vehiculo) {
        vehiculo.setEstado(new EstadoEnEspera());
    }

    @Override
    public String getNombre() {
        return "En Reparacion";
    }
}