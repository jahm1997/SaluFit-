# Herencia en FitLife

## ¿Qué es la Herencia?
La herencia es un pilar fundamental de la Programación Orientada a Objetos que permite crear nuevas clases basadas en clases existentes, heredando sus atributos y comportamientos.

## Implementación en FitLife

### Jerarquía de Clases

#### 1. Clase Base: `Usuario`
```java
public class Usuario {
    protected String nombre;
    protected int edad;
    protected double peso;
    protected double altura;
    
    public double calcularIMC() {
        return peso / (altura * altura);
    }
    
    // Otros métodos comunes...
}
```

#### 2. Clase Derivada: `UsuarioPremium`
```java
public class UsuarioPremium extends Usuario {
    private PlanEntrenamiento planEntrenamiento;
    private Dieta dieta;
    
    // Métodos adicionales para usuarios premium
    public void agregarEjercicio(Ejercicio ejercicio) {
        planEntrenamiento.agregarEjercicio(ejercicio);
    }
    
    public void agregarComida(Comida comida) {
        dieta.agregarComida(comida);
    }
}
```

### Beneficios en la Aplicación
1. **Reutilización de Código**: La clase `UsuarioPremium` hereda atributos y métodos de `Usuario` sin necesidad de reescribirlos.
2. **Extensibilidad**: Fácil de añadir nuevos tipos de usuarios en el futuro.
3. **Mantenimiento**: Los cambios en la clase base afectan a todas las clases derivadas.

### Ejemplo de Uso
```java
// Crear un usuario premium
UsuarioPremium usuario = new UsuarioPremium("Juan", 25, 70.5, 1.75);

// Usar métodos heredados
double imc = usuario.calcularIMC();

// Usar métodos específicos de UsuarioPremium
usuario.agregarEjercicio(new Cardio("Correr", 30));
```

## Relaciones de Herencia
- `Usuario` (clase padre)
  - `UsuarioPremium` (clase hija)

## Buenas Prácticas
1. Usar `protected` para atributos que necesitan ser accesibles por las clases hijas.
2. Sobrescribir métodos cuando sea necesario cambiar el comportamiento heredado.
3. Usar `@Override` para mayor claridad al sobrescribir métodos.

## Ejercicio Propuesto
Crea una nueva clase `UsuarioGratuito` que herede de `Usuario` pero con funcionalidades limitadas en comparación con `UsuarioPremium`.
