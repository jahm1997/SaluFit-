package App;

import java.util.ArrayList;

/**
 *
 * @author Esteb
 */
public class PlanEntrenamiento {

    private ArrayList<Ejercicio> ejercicios = new ArrayList<>();

    public void agregarEjercicio(Ejercicio e) {
        ejercicios.add(e);
    }

    public void iniciarRutina() {
        for (Ejercicio e : ejercicios) {
            System.out.println("Ejercicio: " + e.getNombre() + " - " + e.getDuracion() + " min");
        }
    }

    public int calcularDuracionTotal() {
        int total = 0;
        for (Ejercicio e : ejercicios) {
            total += e.getDuracion();
        }
        return total;
    }
    
    /**
     * Calcula el total de calorías quemadas en todo el plan de entrenamiento
     * @return Total de calorías quemadas
     */
    public int calcularCaloriasQuemadas() {
        int totalCalorias = 0;
        for (Ejercicio ejercicio : ejercicios) {
            // Asegurarse de que las calorías estén calculadas
            ejercicio.setIntensidad(ejercicio.getIntensidad());
            totalCalorias += ejercicio.getCaloriasQuemadas();
        }
        return totalCalorias;
    }
}
