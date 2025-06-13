# Abstracción en FitLife

## ¿Qué es la Abstracción?
La abstracción es el proceso de ocultar los detalles de implementación y mostrar solo la funcionalidad al usuario. Permite enfocarse en lo que hace un objeto en lugar de cómo lo hace.

## Implementación en FitLife

### 1. Clases Abstractas

#### Clase Abstracta `Ejercicio`
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
    
    // Otros métodos concretos...
    public void setIntensidad(int intensidad) {
        if (intensidad >= 1 && intensidad <= 10) {
            this.intensidad = intensidad;
        } else {
            throw new IllegalArgumentException("La intensidad debe estar entre 1 y 10");
        }
    }
}
```

### 2. Interfaces

#### Interfaz `Saludable`
```java
public interface Saludable {
    double calcularIMC();
    String obtenerEstadoSalud();
    List<String> obtenerRecomendaciones();
}
```

#### Implementación en `Usuario`
```java
public class Usuario implements Saludable {
    // Atributos...
    
    @Override
    public double calcularIMC() {
        return peso / (altura * altura);
    }
    
    @Override
    public String obtenerEstadoSalud() {
        double imc = calcularIMC();
        if (imc < 18.5) return "Bajo peso";
        if (imc < 24.9) return "Peso normal";
        if (imc < 29.9) return "Sobrepeso";
        return "Obesidad";
    }
    
    @Override
    public List<String> obtenerRecomendaciones() {
        List<String> recomendaciones = new ArrayList<>();
        String estado = obtenerEstadoSalud();
        
        switch(estado) {
            case "Bajo peso":
                recomendaciones.add("Aumentar la ingesta calórica");
                recomendaciones.add("Consumir más proteínas");
                break;
            case "Peso normal":
                recomendaciones.add("Mantener hábitos saludables");
                recomendaciones.add("Ejercicio regular");
                break;
            // Otros casos...
        }
        
        return recomendaciones;
    }
}
```

## Beneficios en la Aplicación
1. **Ocultamiento de la complejidad**: Los usuarios de las clases solo necesitan conocer la interfaz, no la implementación.
2. **Código más mantenible**: Los cambios en la implementación no afectan el código que usa la interfaz.
3. **Facilita la creación de pruebas unitarias**: Se pueden crear mocks que implementen las interfaces.

## Ejemplo de Uso
```java
// Usando la abstracción a través de la interfaz Saludable
public class InformeSalud {
    public static void mostrarInforme(Saludable persona) {
        System.out.println("Estado de salud: " + persona.obtenerEstadoSalud());
        System.out.println("IMC: " + String.format("%.1f", persona.calcularIMC()));
        
        System.out.println("\nRecomendaciones:");
        for (String rec : persona.obtenerRecomendaciones()) {
            System.out.println("• " + rec);
        }
    }
}

// Uso
Usuario usuario = new Usuario("Ana", 28, 65.5, 1.68);
InformeSalud.mostrarInforme(usuario);
```

## Buenas Prácticas
1. Usar clases abstractas cuando haya una relación "es-un" y comportamiento común.
2. Usar interfaces para definir contratos que pueden ser implementados por clases no relacionadas.
3. Mantener las interfaces pequeñas y enfocadas en una sola responsabilidad.

## Ejercicio Propuesto
Crea una nueva interfaz `Notificable` con métodos como `enviarRecordatorio(String mensaje)` e implementa esta interfaz en la clase `Usuario` para enviar notificaciones sobre rutinas de ejercicio.
