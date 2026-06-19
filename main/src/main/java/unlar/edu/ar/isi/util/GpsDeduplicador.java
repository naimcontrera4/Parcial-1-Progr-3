package unlar.edu.ar.isi.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GpsDeduplicador {
    public List<String> limpiarDuplicados(List<String> reportesConDuplicados) {
        Set<String> unicos = new HashSet<>();
        List<String> limpios = new ArrayList<>();
        
        for (int i = 0; i < reportesConDuplicados.size(); i++) {
            String reporte = reportesConDuplicados.get(i);
            if (unicos.add(reporte)) {
                limpios.add(reporte);
            }
        }
        return limpios;
    }
}