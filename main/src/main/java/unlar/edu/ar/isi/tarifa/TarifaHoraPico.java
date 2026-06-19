package unlar.edu.ar.isi.tarifa;

public class TarifaHoraPico implements EstrategiaTarifa {
    @Override
    public double calcularCosto(double tarifaBase, int minutos) {
        return (tarifaBase * minutos) * 1.40;
    }
}