
package App;

/**
 *
 * @author Esteb
 */
public class Cardio extends Ejercicio {
    private static final int CALORIAS_POR_MINUTO_BASE = 8;

    public Cardio(String nombre, int duracion) {
        super(nombre, duracion);
    }

    @Override
    protected void calcularCaloriasQuemadas() {
        // Cardio exercises burn more calories with higher intensity
        caloriasQuemadas = duracion * CALORIAS_POR_MINUTO_BASE * (intensidad / 5);
    }
}
