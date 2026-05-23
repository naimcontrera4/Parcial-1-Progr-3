package unlar.edu.ar.isi.model;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter

public class UsuarioPremium extends Usuario{
    
    public void descuentoTarifa(Vehiculo vehiculo){
        double tarifa=vehiculo.getTarifa();
        double tarifadescuento=tarifa*0.9;
        vehiculo.setTarifa(tarifadescuento);
    }


}
