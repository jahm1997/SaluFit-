
package App;

/**
 *
 * @author Esteb
 */
public class Estiramiento extends Ejercicio {
    public Estiramiento(String nombre, int duracion) {
        super(nombre, duracion);
    }
    
    @Override
    protected void calcularCaloriasQuemadas() {
        // Los ejercicios de estiramiento queman menos calorías
        // Fórmula: duración * (2 + intensidad/2)
        this.caloriasQuemadas = (int)(duracion * (2 + (intensidad / 2.0)));
    }
}
