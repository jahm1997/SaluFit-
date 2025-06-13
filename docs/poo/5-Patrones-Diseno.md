# Patrones de Diseño en FitLife

## 1. Factory Method

### Implementación: Creación de Ejercicios

#### Clase `EjercicioFactory`
```java
public class EjercicioFactory {
    public static Ejercicio crearEjercicio(String tipo, String nombre, int duracion) {
        return switch (tipo.toLowerCase()) {
            case "cardio" -> new Cardio(nombre, duracion);
            case "fuerza" -> new Fuerza(nombre, duracion, 3, 10); // valores por defecto
            case "flexibilidad" -> new Flexibilidad(nombre, duracion);
            default -> throw new IllegalArgumentException("Tipo de ejercicio no soportado: " + tipo);
        };
    }
}
```

### Uso en la Aplicación
```java
// Crear diferentes tipos de ejercicios sin conocer las clases concretas
Ejercicio cardio = EjercicioFactory.crearEjercicio("cardio", "Correr", 30);
Ejercicio fuerza = EjercicioFactory.crearEjercicio("fuerza", "Press banca", 45);
```

## 2. Strategy

### Implementación: Estrategias de Cálculo de Calorías

#### Interfaz `EstrategiaCalculoCalorias`
```java
public interface EstrategiaCalculoCalorias {
    int calcularCalorias(int duracion, int intensidad, int... parametrosAdicionales);
}
```

#### Implementaciones Concretas
```java
public class EstrategiaCardio implements EstrategiaCalculoCalorias {
    @Override
    public int calcularCalorias(int duracion, int intensidad, int... params) {
        return (int)(duracion * 8 * (intensidad / 5.0));
    }
}

public class EstrategiaFuerza implements EstrategiaCalculoCalorias {
    @Override
    public int calcularCalorias(int duracion, int intensidad, int... params) {
        int series = params.length > 0 ? params[0] : 3;
        int repeticiones = params.length > 1 ? params[1] : 10;
        return (int)(duracion * 5 * (intensidad / 5.0) * (series * repeticiones / 10.0));
    }
}
```

### Uso en la Clase `Ejercicio`
```java
public abstract class Ejercicio {
    protected String nombre;
    protected int duracion;
    protected int intensidad;
    protected EstrategiaCalculoCalorias estrategiaCalculo;
    
    public Ejercicio(String nombre, int duracion, EstrategiaCalculoCalorias estrategia) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.estrategiaCalculo = estrategia;
    }
    
    public int calcularCaloriasQuemadas() {
        return estrategiaCalculo.calcularCalorias(duracion, intensidad);
    }
    
    // Otros métodos...
}
```

## 3. Observer

### Implementación: Notificaciones de Progreso

#### Interfaz `ObservadorProgreso`
```java
public interface ObservadorProgreso {
    void actualizar(Usuario usuario, String mensaje);
}
```

#### Clase `SujetoProgreso`
```java
public class SujetoProgreso {
    private List<ObservadorProgreso> observadores = new ArrayList<>();
    
    public void agregarObservador(ObservadorProgreso observador) {
        observadores.add(observador);
    }
    
    public void notificar(Usuario usuario, String mensaje) {
        for (ObservadorProgreso obs : observadores) {
            obs.actualizar(usuario, mensaje);
        }
    }
}
```

#### Implementación en `Usuario`
```java
public class Usuario {
    private SujetoProgreso sujetoProgreso = new SujetoProgreso();
    private String nombre;
    private double peso;
    
    public void agregarObservador(ObservadorProgreso observador) {
        sujetoProgreso.agregarObservador(observador);
    }
    
    public void actualizarPeso(double nuevoPeso) {
        this.peso = nuevoPeso;
        String mensaje = String.format("Peso actualizado a %.1f kg", nuevoPeso);
        sujetoProgreso.notificar(this, mensaje);
    }
}
```

## Beneficios en la Aplicación
1. **Flexibilidad**: Fácil de extender con nuevos tipos de ejercicios o estrategias.
2. **Bajo acoplamiento**: Las clases no dependen de implementaciones concretas.
3. **Mantenibilidad**: Las modificaciones tienen un impacto localizado.

## Ejercicio Propuesto
Implementa el patrón Observer para notificar cuando un usuario completa un ejercicio, mostrando un mensaje con las calorías quemadas.
