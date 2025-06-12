
package App;

/**
 *
 * @author Esteb
 */
public abstract class Ejercicio {
    protected String nombre;
    protected int duracion;
    protected int intensidad;  // 1-10 scale
    protected int caloriasQuemadas;

    public Ejercicio(String nombre, int duracion) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.intensidad = 5;  // default intensity
        calcularCaloriasQuemadas();
    }

    public String getNombre() {
        return nombre;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setIntensidad(int intensidad) {
        if (intensidad < 1 || intensidad > 10) {
            throw new IllegalArgumentException("La intensidad debe estar entre 1 y 10");
        }
        this.intensidad = intensidad;
        calcularCaloriasQuemadas();
    }

    public int getIntensidad() {
        return intensidad;
    }

    public int getCaloriasQuemadas() {
        return caloriasQuemadas;
    }

    // Template method pattern
    protected abstract void calcularCaloriasQuemadas();
    
    // Factory method for exercise creation
    public static Ejercicio crearEjercicio(String tipo, String nombre, int duracion) {
        switch (tipo.toLowerCase()) {
            case "cardio":
                return new Cardio(nombre, duracion);
            case "fuerza":
                return new Fuerza(nombre, duracion);
            case "estiramiento":
                return new Estiramiento(nombre, duracion);
            default:
                throw new IllegalArgumentException("Tipo de ejercicio no válido");
        }
    }

    @Override
    public String toString() {
        return String.format("%s (Duración: %d min, Intensidad: %d/10, Calorías: %d)",
                nombre, duracion, intensidad, caloriasQuemadas);
    }
}
