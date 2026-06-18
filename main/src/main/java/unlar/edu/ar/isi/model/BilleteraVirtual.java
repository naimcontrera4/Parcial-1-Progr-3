package unlar.edu.ar.isi.model;

public class BilleteraVirtual implements MetodoPago {
    @Override
    public void procesarPago(double monto) {
        System.out.printf("Cobro exitoso de $%.2f realizado con BILLETERA\n", monto);
    }
}