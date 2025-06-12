
package App;

/**
 *
 * @author Esteb
 */
public class UsuarioPremium {
    private String nombre;
    private int edad;
    private double peso, altura;
    private Dieta dieta;
    private PlanEntrenamiento plan;
    private double imc;
    private String estadoSalud;

    public UsuarioPremium(String nombre, int edad, double peso, double altura) {
        this.nombre = nombre;
        this.edad = edad;
        setPeso(peso);
        setAltura(altura);
        this.dieta = new Dieta();
        this.plan = new PlanEntrenamiento();
        calcularIMC();
    }

    public void setPeso(double peso) {
        if (peso <= 0) {
            throw new IllegalArgumentException("El peso debe ser positivo");
        }
        this.peso = peso;
        calcularIMC();
    }

    public void setAltura(double altura) {
        if (altura <= 0) {
            throw new IllegalArgumentException("La altura debe ser positiva");
        }
        this.altura = altura;
        calcularIMC();
    }

    private void calcularIMC() {
        this.imc = peso / (altura * altura);
        if (imc < 18.5) {
            estadoSalud = "Bajo peso";
        } else if (imc < 24.9) {
            estadoSalud = "Peso normal";
        } else if (imc < 29.9) {
            estadoSalud = "Sobrepeso";
        } else {
            estadoSalud = "Obesidad";
        }
    }

    public double getIMC() {
        return imc;
    }

    public String getEstadoSalud() {
        return estadoSalud;
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

    public double getPeso() {
        return peso;
    }

    public double getAltura() {
        return altura;
    }

    public int getEdad() {
        return edad;
    }

    public String generarReporteSemanal() {
        int caloriasConsumidas = dieta.calcularCaloriasTotales();
        int caloriasQuemadas = plan.calcularCaloriasQuemadas();
        int balanceCalorico = caloriasConsumidas - caloriasQuemadas;

        StringBuilder reporte = new StringBuilder()
            .append("Reporte de ").append(nombre.toUpperCase()).append(":\n")
            .append(String.format("IMC: %.2f (%s)\n", imc, estadoSalud))
            .append("Calorías consumidas: ").append(caloriasConsumidas).append("\n")
            .append("Calorías quemadas: ").append(caloriasQuemadas).append("\n")
            .append("Balance calórico: ").append(balanceCalorico).append("\n")
            .append("Duración total de ejercicios: ").append(plan.calcularDuracionTotal()).append(" minutos");

        return reporte.toString();
    }
}

