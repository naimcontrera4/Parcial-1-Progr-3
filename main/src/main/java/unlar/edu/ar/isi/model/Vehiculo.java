package unlar.edu.ar.isi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import unlar.edu.ar.isi.estado.EstadoVehiculo;
import unlar.edu.ar.isi.estado.EstadoEnEspera;

@Data
@AllArgsConstructor
public abstract class Vehiculo implements Comparable<Vehiculo> {
    private String patente;
    private double porcentajeBateria;
    private double tarifaBase;
    private EstadoVehiculo estado;

    public Vehiculo() {
        this.estado = new EstadoEnEspera();
    }

    public Vehiculo(String patente, double porcentajeBateria, double tarifaBase) {
        this.patente = patente;
        this.porcentajeBateria = porcentajeBateria;
        this.tarifaBase = tarifaBase;
        this.estado = new EstadoEnEspera();
    }

    public void setEstado(EstadoVehiculo estado) {
        this.estado = estado;
    }

    public EstadoVehiculo getEstado() {
        return this.estado;
    }

    @Override
    public int compareTo(Vehiculo otro) {
        return Double.compare(this.porcentajeBateria, otro.getPorcentajeBateria());
    }
}