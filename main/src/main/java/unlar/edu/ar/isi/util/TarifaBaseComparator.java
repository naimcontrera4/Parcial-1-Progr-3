package unlar.edu.ar.isi.util;

import unlar.edu.ar.isi.model.Vehiculo;
import java.util.Comparator;

public class TarifaBaseComparator implements Comparator<Vehiculo> {
    @Override
    public int compare(Vehiculo v1, Vehiculo v2) {
        return Double.compare(v2.getTarifaBase(), v1.getTarifaBase());
    }
}