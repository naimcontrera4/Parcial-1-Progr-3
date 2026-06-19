package unlar.edu.ar.isi.estado;

import unlar.edu.ar.isi.model.Vehiculo;

public interface EstadoVehiculo {
    void desbloquear(Vehiculo vehiculo);
    void finalizarViaje(Vehiculo vehiculo);
    void reparar(Vehiculo vehiculo);
    String getNombre();
}