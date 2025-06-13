# Encapsulamiento en FitLife

## ¿Qué es el Encapsulamiento?
El encapsulamiento es el mecanismo que permite restringir el acceso directo a los componentes de un objeto, protegiendo los datos internos de modificaciones no autorizadas.

## Implementación en FitLife

### 1. Atributos Privados
```java
public class Usuario {
    private String nombre;
    private int edad;
    private double peso;
    private double altura;
    
    // Resto de la implementación...
}
```

### 2. Métodos de Acceso (Getters y Setters)
```java
public String getNombre() {
    return nombre;
}

public void setNombre(String nombre) {
    if (nombre != null && !nombre.trim().isEmpty()) {
        this.nombre = nombre.trim();
    } else {
        throw new IllegalArgumentException("El nombre no puede estar vacío");
    }
}

public double getIMC() {
    return peso / (altura * altura);
}
```

### 3. Validación en los Setters
```java
public void setEdad(int edad) {
    if (edad > 0 && edad < 120) {
        this.edad = edad;
    } else {
        throw new IllegalArgumentException("La edad debe estar entre 1 y 119 años");
    }
}

public void setPeso(double peso) {
    if (peso > 0 && peso < 500) {
        this.peso = peso;
    } else {
        throw new IllegalArgumentException("El peso debe ser un valor positivo menor a 500 kg");
    }
}
```

## Beneficios en la Aplicación
1. **Integridad de Datos**: Se asegura que los atributos siempre tengan valores válidos.
2. **Flexibilidad**: Permite cambiar la implementación interna sin afectar el código que usa la clase.
3. **Control de Acceso**: Restringe cómo y cuándo se pueden modificar los datos.

## Ejemplo de Uso
```java
Usuario usuario = new Usuario();
try {
    usuario.setNombre("Ana");
    usuario.setEdad(25);
    usuario.setPeso(65.5);
    usuario.setAltura(1.68);
    
    System.out.println("IMC: " + usuario.getIMC());
} catch (IllegalArgumentException e) {
    System.err.println("Error: " + e.getMessage());
}
```

## Buenas Prácticas
1. Hacer todos los atributos privados por defecto.
2. Proporcionar getters solo para los atributos que necesitan ser leídos desde fuera.
3. Usar setters para validar datos antes de asignarlos.
4. Documentar las restricciones de los métodos.

## Ejercicio Propuesto
Añade validación al método `setAltura()` para asegurar que la altura esté entre 0.5 y 2.5 metros.
