package App;

// Aplicación FitLife usando POO con JFrame
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.Color;
import java.net.URL;
import javax.swing.border.Border;

public class FitLifeGUI extends JFrame {

    private JTextField nombreField, edadField, pesoField, alturaField;
    private JTextField ejercicioField, duracionField;
    private JComboBox<String> tipoEjercicioBox;
    private JTextField comidaField, caloriasField;
    private JTextArea reporteArea;
    private DefaultListModel<String> listaComidas, listaEjercicios;

    // UI Colors
    private static final Color PRIMARY_COLOR = new Color(76, 175, 80);  // Material Green
    private static final Color SECONDARY_COLOR = new Color(33, 150, 243);  // Material Blue
    private static final Color BACKGROUND_COLOR = new Color(240, 255, 240);  // Light Green
    private static final Color PANEL_COLOR = new Color(255, 255, 255);  // White
    private static final Color BUTTON_COLOR = new Color(33, 150, 243);  // Material Blue
    
    // Colores para los niveles de intensidad
    private static final Color colorR1 = new Color(200, 255, 200);  // Verde claro para intensidad baja
    private static final Color colorR3 = new Color(255, 255, 150);  // Amarillo para intensidad media
    private static final Color colorR5 = new Color(255, 180, 180);  // Rojo claro para intensidad alta

    private UsuarioPremium usuario;

    public FitLifeGUI() {
        setTitle("FitLife - App Salud");
        setSize(800, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Center on screen

        FondoPanel PanelFondo = new FondoPanel();
        PanelFondo.setLayout(new BorderLayout());

        setContentPane(PanelFondo);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Datos Usuario", crearPanelUsuario());
        tabs.addTab("Dieta", crearPanelDieta());
        tabs.addTab("Ejercicios", crearPanelEjercicio());
        tabs.addTab("Reporte", crearPanelReporte());

        tabs.setOpaque(false);

        PanelFondo.add(tabs, BorderLayout.CENTER);

        // Apply modern styling to tabs
        for (int i = 0; i < tabs.getTabCount(); i++) {
            tabs.setBackgroundAt(i, SECONDARY_COLOR);
            tabs.setForegroundAt(i, Color.WHITE);
        }
        
        // Set custom tab layout
        tabs.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        tabs.setFont(new Font("Arial", Font.BOLD, 14));

        setVisible(true);

    }

    private JPanel crearPanelUsuario() {
        JPanel panel = new JPanel(new GridLayout(6, 2));

        nombreField = new JTextField();
        edadField = new JTextField();
        pesoField = new JTextField();
        alturaField = new JTextField();

        panel.add(new JLabel("Nombre:"));
        panel.add(nombreField);
        panel.add(new JLabel("Edad:"));
        panel.add(edadField);
        panel.add(new JLabel("Peso (kg):"));
        panel.add(pesoField);
        panel.add(new JLabel("Altura (m):"));
        panel.add(alturaField);
        panel.setBackground(colorR3);

        JButton btnRegistrar = new JButton("Registrar Usuario");
        btnRegistrar.addActionListener(e -> registrarUsuario());
        panel.add(btnRegistrar);

        JButton imcButton = new JButton("Calcular IMC");
        imcButton.addActionListener(e -> calcularIMC());
        panel.add(imcButton); // o donde estés organizando tus botones

        JButton btnConsejo = new JButton("Consejo");
        panel.add(btnConsejo);

        // Consejos de entrenamiento
        String[] consejos = {
            "Recuerda calentar antes de entrenar para evitar lesiones.",
            "Hidrátate adecuadamente antes, durante y después del ejercicio.",
            "Mantén una buena postura durante todo el entrenamiento.",
            "Escucha a tu cuerpo: si sientes dolor, detente.",
            "La constancia es clave: entrena al menos 3 veces por semana.",
            "Combina ejercicios de fuerza, cardio y flexibilidad.",
            "Duerme bien para mejorar tu rendimiento físico.",
            "No te saltes los días de descanso, también son parte del progreso."
        };

        btnConsejo.addActionListener(e -> {
            int indice = (int) (Math.random() * consejos.length);
            String consejo = consejos[indice];
            JOptionPane.showMessageDialog(null, consejo, "Consejo de Entrenamiento", JOptionPane.INFORMATION_MESSAGE);
        });

        btnConsejo.setBackground(colorR5); // Azul pastel
        btnConsejo.setForeground(Color.BLACK);              // Texto negro
        btnConsejo.setFocusPainted(false);                  // Quita borde al enfocar
        btnConsejo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        btnConsejo.setContentAreaFilled(true);
        btnConsejo.setOpaque(true);

        imcButton.setBackground(colorR5); // Azul pastel
        imcButton.setForeground(Color.BLACK);              // Texto negro
        imcButton.setFocusPainted(false);                  // Quita borde al enfocar
        imcButton.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        imcButton.setContentAreaFilled(true);
        imcButton.setOpaque(true);

        btnRegistrar.setBackground(colorR5); // Azul pastel
        btnRegistrar.setForeground(Color.BLACK);              // Texto negro
        btnRegistrar.setFocusPainted(false);                  // Quita borde al enfocar
        btnRegistrar.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        btnRegistrar.setContentAreaFilled(true);
        btnRegistrar.setOpaque(true);

        return panel;

    }

    private JPanel crearPanelDieta() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel entrada = new JPanel(new GridLayout(3, 2));
        comidaField = new JTextField();
        caloriasField = new JTextField();
        panel.setBackground(colorR1);

        entrada.add(new JLabel("Nombre de comida:"));
        entrada.add(comidaField);
        entrada.add(new JLabel("Calorías:"));
        entrada.add(caloriasField);
        entrada.setBackground(colorR3);

        JButton btnAgregar = new JButton("Agregar comida");
        btnAgregar.addActionListener(e -> agregarComida());
        entrada.add(btnAgregar);
        btnAgregar.setBackground(colorR5);

        panel.add(entrada, BorderLayout.NORTH);

        listaComidas = new DefaultListModel<>();
        JList<String> comidasList = new JList<>(listaComidas);
        panel.add(new JScrollPane(comidasList), BorderLayout.CENTER);

        comidasList.setBackground(new Color(245, 255, 250));

        JScrollPane scroll = new JScrollPane(comidasList);
        scroll.getViewport().setBackground(new Color(245, 255, 250)); // Misma tonalidad
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearPanelEjercicio() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel entrada = new JPanel(new GridLayout(4, 2));
        ejercicioField = new JTextField();
        duracionField = new JTextField();
        tipoEjercicioBox = new JComboBox<>(new String[]{"Cardio", "Fuerza", "Estiramiento"});

        entrada.add(new JLabel("Nombre de ejercicio:"));
        entrada.add(ejercicioField);
        entrada.add(new JLabel("Duración (min):"));
        entrada.add(duracionField);
        entrada.add(new JLabel("Tipo de ejercicio:"));
        entrada.add(tipoEjercicioBox);

        entrada.setBackground(colorR3);

        tipoEjercicioBox.setBackground(new Color(230, 255, 240));  // Fondo verde claro
        tipoEjercicioBox.setForeground(new Color(0, 102, 51));     // Texto verde oscuro
        tipoEjercicioBox.setOpaque(true);

        panel.setBackground(colorR1);

        JButton btnAgregar = new JButton("Agregar ejercicio");
        btnAgregar.addActionListener(e -> agregarEjercicio());
        entrada.add(btnAgregar);

        btnAgregar.setBackground(colorR5); // Azul pastel
        btnAgregar.setForeground(Color.BLACK);              // Texto negro
        btnAgregar.setFocusPainted(false);                  // Quita borde al enfocar
        btnAgregar.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        btnAgregar.setContentAreaFilled(true);
        btnAgregar.setOpaque(true);

        panel.add(entrada, BorderLayout.NORTH);

        listaEjercicios = new DefaultListModel<>();
        JList<String> ejerciciosList = new JList<>(listaEjercicios);
        panel.add(new JScrollPane(ejerciciosList), BorderLayout.CENTER);

        ejerciciosList.setBackground(new Color(245, 255, 250));

        JScrollPane scroll = new JScrollPane(ejerciciosList);
        scroll.getViewport().setBackground(new Color(245, 255, 250)); // Misma tonalidad
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearPanelReporte() {
        JPanel panel = new JPanel(new BorderLayout());
        reporteArea = new JTextArea();
        reporteArea.setEditable(false);
        JButton btnGenerar = new JButton("Generar Reporte");
        btnGenerar.addActionListener(e -> generarReporte());
        reporteArea.setBackground(colorR3);

        btnGenerar.setBackground(colorR5); // Azul pastel
        btnGenerar.setForeground(Color.BLACK);              // Texto negro
        btnGenerar.setFocusPainted(false);                  // Quita borde al enfocar
        btnGenerar.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        btnGenerar.setContentAreaFilled(true);
        btnGenerar.setOpaque(true);

        panel.add(new JScrollPane(reporteArea), BorderLayout.CENTER);
        panel.add(btnGenerar, BorderLayout.SOUTH);
        return panel;

    }

//Métodos//
    private void calcularIMC() {
        if (usuario == null) {
            JOptionPane.showMessageDialog(this, 
                "Primero registre un usuario", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Using the IMC calculation from UsuarioPremium
            double imc = usuario.getIMC();
            String estado = usuario.getEstadoSalud();
            
            StringBuilder mensaje = new StringBuilder()
                .append(String.format("IMC: %.2f\n", imc))
                .append(String.format("Estado: %s\n\n", estado));

            // Add recommendations based on IMC
            if (imc < 18.5) {
                mensaje.append("Recomendaciones:\n")
                       .append("- Aumentar la ingesta calórica\n")
                       .append("- Consumir proteínas de calidad\n")
                       .append("- Realizar ejercicios de fuerza");
            } else if (imc < 24.9) {
                mensaje.append("Recomendaciones:\n")
                       .append("- Mantener hábitos saludables\n")
                       .append("- Realizar ejercicio regular\n")
                       .append("- Mantener una dieta balanceada");
            } else if (imc < 29.9) {
                mensaje.append("Recomendaciones:\n")
                       .append("- Aumentar actividad física\n")
                       .append("- Reducir carbohidratos refinados\n")
                       .append("- Controlar porciones de comida");
            } else {
                mensaje.append("Recomendaciones:\n")
                       .append("- Consultar con un profesional de la salud\n")
                       .append("- Comenzar actividad física gradual\n")
                       .append("- Adoptar una dieta saludable");
            }

            JOptionPane.showMessageDialog(this, 
                mensaje.toString(), 
                "Resultados IMC", 
                JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al calcular IMC: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void registrarUsuario() {
        try {
            String nombre = nombreField.getText();
            int edad = Integer.parseInt(edadField.getText());
            double peso = Double.parseDouble(pesoField.getText());
            double altura = Double.parseDouble(alturaField.getText());

            usuario = new UsuarioPremium(nombre, edad, peso, altura);
            JOptionPane.showMessageDialog(this, "Usuario registrado con éxito.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error en los datos del usuario.");
        }
    }

    private void agregarComida() {
        if (usuario == null) {
            JOptionPane.showMessageDialog(this, "Primero registre un usuario.");
            return;
        }

        try {
            String nombre = comidaField.getText();
            int calorias = Integer.parseInt(caloriasField.getText());
            usuario.getDieta().agregarComida(new Comida(nombre, calorias));
            listaComidas.addElement(nombre + " - " + calorias + " cal");
            comidaField.setText("");
            caloriasField.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al agregar comida.");
        }
    }

    private void agregarEjercicio() {
        if (usuario == null) {
            JOptionPane.showMessageDialog(this, "Primero registre un usuario.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String nombre = ejercicioField.getText().trim();
            if (nombre.isEmpty()) {
                throw new IllegalArgumentException("El nombre del ejercicio no puede estar vacío");
            }

            int duracion = Integer.parseInt(duracionField.getText().trim());
            if (duracion <= 0) {
                throw new IllegalArgumentException("La duración debe ser mayor a 0");
            }

            String tipo = (String) tipoEjercicioBox.getSelectedItem();
            
            // Using factory method pattern
            Ejercicio ejercicio = Ejercicio.crearEjercicio(tipo, nombre, duracion);
            
            // Set random intensity between 3 and 8
            ejercicio.setIntensidad(3 + (int)(Math.random() * 6));

            usuario.getPlanEntrenamiento().agregarEjercicio(ejercicio);
            listaEjercicios.addElement(ejercicio.toString());
            
            // Clear fields and show success message
            ejercicioField.setText("");
            duracionField.setText("");
            JOptionPane.showMessageDialog(this, 
                String.format("Ejercicio agregado: %s\nCalorías estimadas: %d", 
                    ejercicio.toString(), ejercicio.getCaloriasQuemadas()),
                "Ejercicio Agregado", 
                JOptionPane.INFORMATION_MESSAGE);
                
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "La duración debe ser un número válido", 
                "Error de Formato", 
                JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Error: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void generarReporte() {
        if (usuario == null) {
            reporteArea.setText("Primero registre un usuario.");
        } else {
            usuario.getPlanEntrenamiento().iniciarRutina();
            reporteArea.setText(usuario.generarReporteSemanal());
        }
    }

    public static void main(String[] args) {
        new FitLifeGUI();
    }

    class FondoPanel extends JPanel {
    private Image imagen;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

       if (imagen == null) {
        try {
            URL url = getClass().getResource("/imagenes/cesped_fondo.png");
            System.out.println("Ruta cargada: " + url);
            imagen = new ImageIcon(url).getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


        if (imagen != null) {
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
        }
        
    }
}

       

        

    
}
