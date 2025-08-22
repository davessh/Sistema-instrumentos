import javax.swing.*;
import java.awt.*;

public class Sistema extends JFrame {

    private Control control;

    public Sistema() {
        super("Gestión de Instrumentos");
        control = new Control();
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 10, 10));
    }

    private void inicializarComponentes() {
        JButton btnAgregar = new JButton("Agregar Instrumento");
        JButton btnMostrar = new JButton("Mostrar Instrumentos");
        JButton btnEliminar = new JButton("Eliminar Instrumento");
        JButton btnSalir = new JButton("Salir");

        // Acción AGREGAR
        btnAgregar.addActionListener(e -> {
            try {
                int clave = Integer.parseInt(JOptionPane.showInputDialog(this, "Clave:"));
                String nombre = JOptionPane.showInputDialog(this, "Nombre:");
                String autor = JOptionPane.showInputDialog(this, "Autor:");

                String[] opcionesProposito = {"Identificar", "Manejar"};
                String proposito = (String) JOptionPane.showInputDialog(
                        this,
                        "Seleccione el propósito:",
                        "Propósito",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opcionesProposito,
                        opcionesProposito[0]
                );

                String[] opcionesTipo = {"Ansiedad", "Estrés", "Ambos"};
                String tipo = (String) JOptionPane.showInputDialog(
                        this,
                        "Seleccione la condición:",
                        "Condición",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opcionesTipo,
                        opcionesTipo[0]
                );

                String condicion = JOptionPane.showInputDialog(this, "Tipo:");

                int resp = JOptionPane.showConfirmDialog(
                        this,
                        "¿Cuenta con confiabilidad?",
                        "Evaluación",
                        JOptionPane.YES_NO_OPTION
                );
                boolean evaluacion = (resp == JOptionPane.YES_OPTION);

                Instrumento inst = new Instrumento(clave, nombre, autor, tipo, proposito, condicion);
                inst.setEvaluacion(evaluacion);

                control.agregarInstrumento(inst);
                control.registrarAutor(autor);

                JOptionPane.showMessageDialog(this, "Instrumento agregado con éxito.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al agregar instrumento.");
            }
        });

        btnMostrar.addActionListener(e -> {
            String[] opcionesMuestra = {"Todos", "Por Autor", "Por Tipo", "Por Condición", "Por Evaluación"};
            String opcion = (String) JOptionPane.showInputDialog(
                    this,
                    "Seleccione cómo mostrar los instrumentos:",
                    "Mostrar Instrumentos",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opcionesMuestra,
                    opcionesMuestra[0]
            );

            if (opcion != null) {
                String resultado = "";
                switch (opcion) {
                    case "Todos":
                        resultado = control.mostrarTodos();
                        break;
                    case "Por Autor":
                        String autor = JOptionPane.showInputDialog(this, "Ingrese el nombre del autor:");
                        resultado = control.buscarPorAutor(autor);
                        break;
                    case "Por Tipo":
                        String tipo = JOptionPane.showInputDialog(this, "Ingrese el tipo de instrumento (p. ej. Test):");
                        resultado = control.buscarPorTipo(tipo);
                        break;
                    case "Por Condición":
                        String condicion = JOptionPane.showInputDialog(this, "Ingrese la condición (p. ej. Estrés):");
                        resultado = control.buscarPorCondicion(condicion);
                        break;
                    case "Por Evaluación":
                        int resp = JOptionPane.showConfirmDialog(this, "Mostrar instrumentos confiables?", "Evaluación", JOptionPane.YES_NO_OPTION);
                        boolean evaluacion = (resp == JOptionPane.YES_OPTION);
                        resultado = control.buscarPorEvaluacion(evaluacion);
                        break;
                }

                if (resultado.isEmpty()) {
                    resultado = "No se encontraron instrumentos con ese criterio.";
                }

                JOptionPane.showMessageDialog(this, resultado, "Lista de Instrumentos", JOptionPane.INFORMATION_MESSAGE);
            }
        });


        btnEliminar.addActionListener(e -> {
            try {
                int clave = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese clave a eliminar:"));
                control.quitarPorClave(clave);
                JOptionPane.showMessageDialog(this, "Instrumento eliminado (si existía).");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar.");
            }
        });

        btnSalir.addActionListener(e -> System.exit(0));

        add(btnAgregar);
        add(btnMostrar);
        add(btnEliminar);
        add(btnSalir);
    }

}
