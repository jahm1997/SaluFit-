
package App;

/**
 *
 * @author Esteb
 */
public class Fuerza extends Ejercicio {
    public Fuerza(String nombre, int duracion) {
        super(nombre, duracion);
    }
    
    @Override
    protected void calcularCaloriasQuemadas() {
        // Los ejercicios de fuerza queman una cantidad media de calorías
        // Fórmula: duración * (4 + intensidad)
        this.caloriasQuemadas = duracion * (4 + intensidad);
    }
}
