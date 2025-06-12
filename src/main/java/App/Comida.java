
package App;

/**
 *
 * @author Esteban
 */
public class Comida {
    private String nombre;
    private int calorias;

    public Comida(String nombre, int calorias) {
        this.nombre = nombre;
        this.calorias = calorias;
    }

    public int getCalorias() {
        return calorias;
    }
}
