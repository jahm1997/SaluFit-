# Polimorfismo en FitLife

## ¿Qué es el Polimorfismo?
El polimorfismo permite que objetos de diferentes clases respondan de manera diferente al mismo mensaje (llamada a método).

## Implementación en FitLife

### 1. Clase Base Abstracta: `Ejercicio`
```java
public abstract class Ejercicio {
    protected String nombre;
    protected int duracion; // en minutos
    protected int intensidad; // 1-10
    
    public Ejercicio(String nombre, int duracion) {
        this.nombre = nombre;
        this.duracion = duracion;
    }
    
    // Método abstracto que debe ser implementado por las subclases
    public abstract int calcularCaloriasQuemadas();
    
    // Método concreto con implementación por defecto
    public String getDescripcion() {
        return String.format("%s - %d min (Intensidad: %d/10)", 
                          nombre, duracion, intensidad);
    }
}
```

### 2. Subclases Específicas
```java
public class Cardio extends Ejercicio {
    public Cardio(String nombre, int duracion) {
        super(nombre, duracion);
    }
    
    @Override
    public int calcularCaloriasQuemadas() {
        // Fórmula específica para ejercicios cardiovasculares
        return (int)(duracion * 8 * (intensidad / 5.0));
    }
}

public class Fuerza extends Ejercicio {
    private int series;
    private int repeticiones;
    
    public Fuerza(String nombre, int duracion, int series, int repeticiones) {
        super(nombre, duracion);
        this.series = series;
        this.repeticiones = repeticiones;
    }
    
    @Override
    public int calcularCaloriasQuemadas() {
        // Fórmula específica para ejercicios de fuerza
        return (int)(duracion * 5 * (intensidad / 5.0) * (series * repeticiones / 10.0));
    }
    
    @Override
    public String getDescripcion() {
        return String.format("%s - %d min, %dx%d (Intensidad: %d/10)", 
                          nombre, duracion, series, repeticiones, intensidad);
    }
}
```

### 3. Uso del Polimorfismo
```java
// Crear una lista de ejercicios de diferentes tipos
List<Ejercicio> rutina = new ArrayList<>();
rutina.add(new Cardio("Correr", 30));
rutina.add(new Fuerza("Sentadillas", 20, 3, 12));
rutina.add(new Cardio("Ciclismo", 45));

// Calcular calorías quemadas usando polimorfismo
int totalCalorias = 0;
for (Ejercicio e : rutina) {
    // Se llama al método apropiado para cada tipo de ejercicio
    int calorias = e.calcularCaloriasQuemadas();
    totalCalorias += calorias;
    System.out.printf("%s - %d cal%n", e.getDescripcion(), calorias);
}
System.out.println("Total de calorías quemadas: " + totalCalorias);
```

## Beneficios en la Aplicación
1. **Extensibilidad**: Fácil de añadir nuevos tipos de ejercicios sin modificar el código existente.
2. **Mantenibilidad**: El código que usa los ejercicios no necesita saber el tipo concreto de ejercicio.
3. **Legibilidad**: El código es más limpio y expresivo.

## Ejercicio Propuesto
Crea una nueva clase `Flexibilidad` que herede de `Ejercicio` con su propia implementación de `calcularCaloriasQuemadas()`.
