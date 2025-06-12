
package App;

/**
 *
 * @author Esteb
 */
public class UsuarioPremium {
    private String nombre;
    private int edad;
    private double peso, altura;
    private Dieta dieta = new Dieta();
    private PlanEntrenamiento plan = new PlanEntrenamiento();

    public UsuarioPremium(String nombre, int edad, double peso, double altura) {
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.altura = altura;
    }

    public Dieta getDieta() {
        return dieta;
    }

    public PlanEntrenamiento getPlanEntrenamiento() {
        return plan;
    }

    public String getNombre() {
        return nombre;
    }

    public String generarReporteSemanal() {
        return "Reporte de " + nombre.toUpperCase() + ":\n"
                + "Total calorías: " + dieta.calcularCaloriasTotales() + "\n"
                + "Total duración de ejercicios: " + plan.calcularDuracionTotal() + " minutos";
    }
}

