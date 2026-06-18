package unlar.edu.ar.isi.model;

public class ProcesamientoDePagos {
    
    public void cobrar(MetodoPago metodoPago, double monto) {
        metodoPago.procesarPago(monto);
    }
}