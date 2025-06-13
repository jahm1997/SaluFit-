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
    private JPanel cardPanel;
    private CardLayout cardLayout;

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

    private JPanel crearPanelBienvenida() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(245, 250, 245));
        
        JLabel titulo = new JLabel("¡Bienvenido a FitLife!", JLabel.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titulo.setForeground(new Color(0, 100, 0));
        titulo.setBorder(BorderFactory.createEmptyBorder(50, 0, 30, 0));
        
        JLabel subtitulo = new JLabel("Tu asistente personal de salud y bienestar", JLabel.CENTER);
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        subtitulo.setForeground(new Color(70, 70, 70));
        
        JButton comenzarBtn = new JButton("¡Comenzar!");
        estilizarBoton(comenzarBtn, PRIMARY_COLOR, Color.WHITE);
        comenzarBtn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        comenzarBtn.setPreferredSize(new Dimension(200, 60));
        comenzarBtn.addActionListener(e -> cardLayout.show(cardPanel, "main"));
        
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(new Color(245, 250, 245));
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(comenzarBtn);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(10, 0, 10, 0);
        
        centerPanel.add(titulo, gbc);
        centerPanel.add(subtitulo, gbc);
        centerPanel.add(Box.createVerticalStrut(30), gbc);
        centerPanel.add(buttonPanel, gbc);
        
        panel.add(centerPanel, BorderLayout.CENTER);
        return panel;
    }
    
    private void estilizarBoton(JButton boton, Color fondo, Color texto) {
        boton.setBackground(fondo);
        boton.setForeground(texto);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        boton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    public FitLifeGUI() {
        setTitle("FitLife - Tu Asistente de Salud");
        setSize(600, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Configuración del sistema de tarjetas
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        
        // Panel de bienvenida
        JPanel welcomePanel = crearPanelBienvenida();
        
        // Panel principal de la aplicación
        JPanel mainPanel = new JPanel(new BorderLayout());
        FondoPanel PanelFondo = new FondoPanel();
        PanelFondo.setLayout(new BorderLayout());
        
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Datos Usuario", crearPanelUsuario());
        tabs.addTab("Dieta", crearPanelDieta());
        tabs.addTab("Ejercicios", crearPanelEjercicio());
        tabs.addTab("Reporte", crearPanelReporte());
        
        // Estilo de las pestañas
        tabs.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabs.setBackground(new Color(240, 240, 240));
        tabs.setForeground(new Color(50, 50, 50));
        
        PanelFondo.add(tabs, BorderLayout.CENTER);
        mainPanel.add(PanelFondo, BorderLayout.CENTER);
        
        // Agregar paneles al cardPanel
        cardPanel.add(welcomePanel, "welcome");
        cardPanel.add(mainPanel, "main");
        
        setContentPane(cardPanel);
        setVisible(true);

    }

    private JPanel crearPanelUsuario() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(colorR3);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.weightx = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Configuración de campos
        JLabel[] etiquetas = {
            new JLabel("Nombre:"),
            new JLabel("Edad:"),
            new JLabel("Peso (kg):"),
            new JLabel("Altura (m):")
        };
        
        JTextField[] campos = {
            nombreField = new JTextField(15),
            edadField = new JTextField(15),
            pesoField = new JTextField(15),
            alturaField = new JTextField(15)
        };
        
        // Añadir etiquetas y campos
        for (int i = 0; i < etiquetas.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            etiquetas[i].setFont(new Font("Segoe UI", Font.PLAIN, 14));
            panel.add(etiquetas[i], gbc);
            
            gbc.gridx = 1;
            campos[i].setPreferredSize(new Dimension(200, 30));
            campos[i].setFont(new Font("Segoe UI", Font.PLAIN, 14));
            panel.add(campos[i], gbc);
        }
        
        // Panel de botones
        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        botonesPanel.setOpaque(false);
        
        JButton btnRegistrar = new JButton("Registrar Usuario");
        btnRegistrar.addActionListener(e -> registrarUsuario());
        estilizarBoton(btnRegistrar, PRIMARY_COLOR, Color.WHITE);
        
        JButton imcButton = new JButton("Calcular IMC");
        imcButton.addActionListener(e -> calcularIMC());
        estilizarBoton(imcButton, SECONDARY_COLOR, Color.WHITE);
        
        JButton btnConsejo = new JButton("Consejo Saludable");
        estilizarBoton(btnConsejo, new Color(255, 160, 0), Color.WHITE);
        
        botonesPanel.add(btnRegistrar);
        botonesPanel.add(imcButton);
        botonesPanel.add(btnConsejo);
        
        gbc.gridx = 0;
        gbc.gridy = etiquetas.length;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 0, 0, 0);
        panel.add(botonesPanel, gbc);

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
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setBackground(colorR1);
        
        // Panel de entrada
        JPanel entrada = new JPanel(new GridBagLayout());
        entrada.setBackground(colorR3);
        entrada.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Campos de texto
        JLabel lblComida = new JLabel("Nombre de comida:");
        comidaField = new JTextField(15);
        JLabel lblCalorias = new JLabel("Calorías:");
        caloriasField = new JTextField(10);
        
        // Establecer fuentes
        Font fuente = new Font("Segoe UI", Font.PLAIN, 14);
        lblComida.setFont(fuente);
        lblCalorias.setFont(fuente);
        comidaField.setFont(fuente);
        caloriasField.setFont(fuente);
        
        // Botón de agregar
        JButton btnAgregar = new JButton("Agregar comida");
        estilizarBoton(btnAgregar, PRIMARY_COLOR, Color.WHITE);
        btnAgregar.addActionListener(e -> agregarComida());
        
        // Configuración de GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Añadir componentes al panel de entrada
        gbc.gridx = 0; gbc.gridy = 0;
        entrada.add(lblComida, gbc);
        
        gbc.gridx = 1; gbc.gridy = 0;
        gbc.weightx = 1.0;
        entrada.add(comidaField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.weightx = 0.0;
        entrada.add(lblCalorias, gbc);
        
        gbc.gridx = 1; gbc.gridy = 1;
        gbc.weightx = 1.0;
        entrada.add(caloriasField, gbc);
        
        gbc.gridx = 1; gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        entrada.add(btnAgregar, gbc);
        
        // Lista de comidas
        listaComidas = new DefaultListModel<>();
        JList<String> comidasList = new JList<>(listaComidas);
        comidasList.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        // Panel de desplazamiento
        JScrollPane scroll = new JScrollPane(comidasList);
        scroll.setBorder(BorderFactory.createTitledBorder("Lista de Comidas"));
        scroll.getViewport().setBackground(Color.WHITE);
        
        // Añadir componentes al panel principal
        panel.add(entrada, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearPanelEjercicio() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setBackground(colorR1);
        
        // Panel de entrada
        JPanel entrada = new JPanel(new GridBagLayout());
        entrada.setBackground(colorR3);
        entrada.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Componentes
        JLabel lblEjercicio = new JLabel("Nombre de ejercicio:");
        ejercicioField = new JTextField(15);
        JLabel lblDuracion = new JLabel("Duración (min):");
        duracionField = new JTextField(5);
        JLabel lblTipo = new JLabel("Tipo de ejercicio:");
        tipoEjercicioBox = new JComboBox<>(new String[]{"Cardio", "Fuerza", "Estiramiento"});
        
        // Establecer fuentes
        Font fuente = new Font("Segoe UI", Font.PLAIN, 14);
        lblEjercicio.setFont(fuente);
        lblDuracion.setFont(fuente);
        lblTipo.setFont(fuente);
        ejercicioField.setFont(fuente);
        duracionField.setFont(fuente);
        tipoEjercicioBox.setFont(fuente);
        
        // Estilo del ComboBox
        tipoEjercicioBox.setBackground(Color.WHITE);
        tipoEjercicioBox.setForeground(new Color(0, 100, 0));
        tipoEjercicioBox.setFocusable(false);
        
        // Botón de agregar
        JButton btnAgregar = new JButton("Agregar ejercicio");
        estilizarBoton(btnAgregar, PRIMARY_COLOR, Color.WHITE);
        btnAgregar.addActionListener(e -> agregarEjercicio());
        
        // Configuración de GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Añadir componentes al panel de entrada
        gbc.gridx = 0; gbc.gridy = 0;
        entrada.add(lblEjercicio, gbc);
        
        gbc.gridx = 1; gbc.gridy = 0;
        gbc.weightx = 1.0;
        entrada.add(ejercicioField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.weightx = 0.0;
        entrada.add(lblDuracion, gbc);
        
        gbc.gridx = 1; gbc.gridy = 1;
        gbc.weightx = 1.0;
        entrada.add(duracionField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.weightx = 0.0;
        entrada.add(lblTipo, gbc);
        
        gbc.gridx = 1; gbc.gridy = 2;
        gbc.weightx = 1.0;
        entrada.add(tipoEjercicioBox, gbc);
        
        gbc.gridx = 1; gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        entrada.add(btnAgregar, gbc);
        
        // Estilo del botón de agregar
        estilizarBoton(btnAgregar, PRIMARY_COLOR, Color.WHITE);
        
        // Lista de ejercicios
        listaEjercicios = new DefaultListModel<>();
        JList<String> ejerciciosList = new JList<>(listaEjercicios);
        ejerciciosList.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        ejerciciosList.setBackground(new Color(245, 255, 250));
        
        // Panel de desplazamiento
        JScrollPane scroll = new JScrollPane(ejerciciosList);
        scroll.setBorder(BorderFactory.createTitledBorder("Lista de Ejercicios"));
        scroll.getViewport().setBackground(new Color(245, 255, 250));
        
        // Añadir componentes al panel principal
        panel.add(entrada, BorderLayout.NORTH);
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
    private void registrarUsuario() {
        try {
            // Validar campos vacíos
            if (nombreField.getText().trim().isEmpty() || 
                edadField.getText().trim().isEmpty() || 
                pesoField.getText().trim().isEmpty() || 
                alturaField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Por favor complete todos los campos.", 
                    "Campos Incompletos", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Validar formato numérico
            int edad;
            double peso, altura;
            
            try {
                edad = Integer.parseInt(edadField.getText().trim());
                peso = Double.parseDouble(pesoField.getText().trim());
                altura = Double.parseDouble(alturaField.getText().trim());
                
                // Validar valores positivos
                if (edad <= 0 || peso <= 0 || altura <= 0) {
                    throw new NumberFormatException();
                }
                
                // Validar altura razonable (en metros)
                if (altura < 0.5 || altura > 2.5) {
                    JOptionPane.showMessageDialog(this, 
                        "Por favor ingrese una altura válida (entre 0.5 y 2.5 metros).", 
                        "Altura Inválida", 
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, 
                    "Por favor ingrese valores numéricos válidos y positivos.", 
                    "Datos Inválidos", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Confirmación antes de registrar
            int confirmacion = JOptionPane.showConfirmDialog(
                this, 
                "¿Está seguro de registrar estos datos?\n" +
                "Nombre: " + nombreField.getText() + "\n" +
                "Edad: " + edad + " años\n" +
                "Peso: " + peso + " kg\n" +
                "Altura: " + altura + " m",
                "Confirmar Registro",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
                
            if (confirmacion == JOptionPane.YES_OPTION) {
                usuario = new UsuarioPremium(nombreField.getText().trim(), edad, peso, altura);
                JOptionPane.showMessageDialog(this, 
                    "¡Usuario registrado exitosamente!", 
                    "Registro Exitoso", 
                    JOptionPane.INFORMATION_MESSAGE);
                
                // Limpiar campos después de registro exitoso
                nombreField.setText("");
                edadField.setText("");
                pesoField.setText("");
                alturaField.setText("");
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Ocurrió un error inesperado: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void calcularIMC() {
        if (usuario == null) {
            JOptionPane.showMessageDialog(this, 
                "Primero debe registrar un usuario.",
                "Usuario no registrado",
                JOptionPane.WARNING_MESSAGE);
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
    
    private void agregarComida() {
        if (usuario == null) {
            JOptionPane.showMessageDialog(this, 
                "Primero debe registrar un usuario.",
                "Usuario no registrado",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Validar campos vacíos
            if (comidaField.getText().trim().isEmpty() || caloriasField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Por favor complete todos los campos de la comida.", 
                    "Campos Incompletos", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Validar formato de calorías
            int calorias;
            try {
                calorias = Integer.parseInt(caloriasField.getText().trim());
                if (calorias <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, 
                    "Por favor ingrese un número válido de calorías (mayor a 0).", 
                    "Calorías Inválidas", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            String nombre = comidaField.getText().trim();
            
            // Confirmar antes de agregar
            int confirmacion = JOptionPane.showConfirmDialog(
                this, 
                String.format("¿Agregar esta comida?\n\n" +
                            "Nombre: %s\n" +
                            "Calorías: %d", nombre, calorias),
                "Confirmar Comida",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
                
            if (confirmacion == JOptionPane.YES_OPTION) {
                usuario.getDieta().agregarComida(new Comida(nombre, calorias));
                listaComidas.addElement(nombre + " - " + calorias + " cal");
                
                // Limpiar campos
                comidaField.setText("");
                caloriasField.setText("");
                
                JOptionPane.showMessageDialog(this, 
                    "¡Comida agregada exitosamente!", 
                    "Éxito", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al agregar comida: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void agregarEjercicio() {
        if (usuario == null) {
            JOptionPane.showMessageDialog(this, 
                "Primero debe registrar un usuario.",
                "Usuario no registrado",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Validar campos vacíos
            if (ejercicioField.getText().trim().isEmpty() || duracionField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Por favor complete todos los campos del ejercicio.", 
                    "Campos Incompletos", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            String nombre = ejercicioField.getText().trim();
            
            // Validar duración
            int duracion;
            try {
                duracion = Integer.parseInt(duracionField.getText().trim());
                if (duracion <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, 
                    "Por favor ingrese una duración válida (mayor a 0 minutos).", 
                    "Duración Inválida", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            String tipo = (String) tipoEjercicioBox.getSelectedItem();
            
            // Crear ejercicio usando el patrón Factory
            Ejercicio ejercicio = Ejercicio.crearEjercicio(tipo, nombre, duracion);
            
            // Establecer intensidad aleatoria entre 3 y 8
            int intensidad = 3 + (int)(Math.random() * 6);
            ejercicio.setIntensidad(intensidad);
            
            // Calcular calorías estimadas
            int caloriasQuemadas = ejercicio.getCaloriasQuemadas();
            
            // Confirmar antes de agregar
            int confirmacion = JOptionPane.showConfirmDialog(
                this, 
                String.format("¿Agregar este ejercicio?\n\n" +
                            "Tipo: %s\n" +
                            "Nombre: %s\n" +
                            "Duración: %d minutos\n" +
                            "Intensidad: %d/10\n" +
                            "Calorías estimadas: %d", 
                            tipo, nombre, duracion, intensidad, caloriasQuemadas),
                "Confirmar Ejercicio",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
                
            if (confirmacion == JOptionPane.YES_OPTION) {
                usuario.getPlanEntrenamiento().agregarEjercicio(ejercicio);
                listaEjercicios.addElement(ejercicio.toString());
                
                // Limpiar campos
                ejercicioField.setText("");
                duracionField.setText("");
                
                JOptionPane.showMessageDialog(this, 
                    String.format("¡Ejercicio agregado exitosamente!\n\n" +
                                "%s\n" +
                                "Calorías estimadas: %d", 
                                ejercicio.toString(), caloriasQuemadas),
                    "Ejercicio Agregado", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Error al agregar ejercicio: " + ex.getMessage(), 
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
