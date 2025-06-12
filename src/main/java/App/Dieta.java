
package App;

import java.util.ArrayList;

/**
 *
 * @author Esteb
 */
public class Dieta {
      private ArrayList<Comida> comidas = new ArrayList<>();

    public void agregarComida(Comida c) {
        comidas.add(c);
    }

    public int calcularCaloriasTotales() {
        int total = 0;
        for (Comida c : comidas) {
            total += c.getCalorias();
        }
        return total;
    }
}
