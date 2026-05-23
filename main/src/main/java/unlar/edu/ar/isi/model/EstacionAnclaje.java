package unlar.edu.ar.isi.model;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class EstacionAnclaje {
    private UUID nombre;
    private List<Vehiculo> disponibles;
    

/* 
    public List<Vehiculo>vehiculosDisponiles(){
        for (Vehiculo vehiculo : disponibles) {
            if(vehiculo.getDisponible()){
                
            }else{

            }
        }
    }
        */
}
