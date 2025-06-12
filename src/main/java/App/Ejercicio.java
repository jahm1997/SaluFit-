
package App;

/**
 *
 * @author Esteb
 */
public abstract class Ejercicio{
    protected String nombre;
    protected int duracion;

    public Ejercicio(String nombre, int duracion) {
        this.nombre = nombre;
        this.duracion = duracion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDuracion() {
        return duracion;
    }
}
