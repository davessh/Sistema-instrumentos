import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Sistema extends JFrame {

    private Control control;

    public Sistema() {
        super("Gestión de Instrumentos");
        control = new Control();
        configurarVentana();
        inicializarBotones();
    }

    private void configurarVentana() {
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 10, 10));
    }

    private void inicializarBotones() {
        JButton btnAgregar = crearBotonAgregar();
        JButton btnMostrar = crearBotonMostrar();
        JButton btnEliminar = crearBotonEliminar();
        JButton btnSalir = crearBotonSalir();

        add(btnAgregar);
        add(btnMostrar);
        add(btnEliminar);
        add(btnSalir);
    }

    private JButton crearBotonAgregar() {
        JButton btn = new JButton("Agregar Instrumento");
        btn.addActionListener(e -> agregarInstrumento());
        return btn;
    }

    private JButton crearBotonMostrar() {
        JButton btn = new JButton("Mostrar Instrumentos");
        btn.addActionListener(e -> mostrarInstrumentos());
        return btn;
    }

    private JButton crearBotonEliminar() {
        JButton btn = new JButton("Eliminar Instrumento");
        btn.addActionListener(e -> eliminarInstrumento());
        return btn;
    }

    private JButton crearBotonSalir() {
        JButton btn = new JButton("Salir");
        btn.addActionListener(e -> System.exit(0));
        return btn;
    }

    private void agregarInstrumento() {
        try {
            int clave = Integer.parseInt(JOptionPane.showInputDialog(this, "Clave:"));
            String nombre = JOptionPane.showInputDialog(this, "Nombre:");
            String autor = JOptionPane.showInputDialog(this, "Autor:");

            String[] opcionesProposito = {"Identificar", "Manejar"};
            String proposito = (String) JOptionPane.showInputDialog(
                    this, "Seleccione el propósito:", "Propósito",
                    JOptionPane.QUESTION_MESSAGE, null, opcionesProposito, opcionesProposito[0]
            );

            String[] opcionesTipo = {"Ansiedad", "Estrés", "Ambos"};
            String tipo = (String) JOptionPane.showInputDialog(
                    this, "Seleccione la condición:", "Condición",
                    JOptionPane.QUESTION_MESSAGE, null, opcionesTipo, opcionesTipo[0]
            );

            String condicion = JOptionPane.showInputDialog(this, "Tipo:");

            int respuesta = JOptionPane.showConfirmDialog(
                    this, "¿Cuenta con confiabilidad?", "Evaluación", JOptionPane.YES_NO_OPTION
            );
            boolean evaluacion = (respuesta == JOptionPane.YES_OPTION);

            Instrumento instrumento = new Instrumento(clave, nombre, autor, tipo, proposito, condicion);
            instrumento.setEvaluacion(evaluacion);

            control.agregarInstrumento(instrumento);
            control.registrarAutor(autor);

            JOptionPane.showMessageDialog(this, "Instrumento agregado con éxito.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al agregar instrumento.");
        }
    }

    private void mostrarInstrumentos() {
        String[] opcionesMuestra = {"Todos", "Por Autor", "Por Tipo", "Por Condición", "Por Evaluación", "Ordenar por Clave", "Ordenar por Autor"};
        String opcion = (String) JOptionPane.showInputDialog(
                this, "Seleccione cómo mostrar los instrumentos:", "Mostrar Instrumentos",
                JOptionPane.QUESTION_MESSAGE, null, opcionesMuestra, opcionesMuestra[0]
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
                case "Ordenar por Clave":
                    resultado = convertirListaAString(control.ordenarPorClave());
                    break;
                case "Ordenar por Autor":
                    resultado = convertirListaAString(control.ordenarPorPrimerAutor());
                    break;
            }

            if (resultado.isEmpty()) {
                resultado = "No se encontraron instrumentos con ese criterio.";
            }

            JOptionPane.showMessageDialog(this, resultado, "Lista de Instrumentos", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void eliminarInstrumento() {
        try {
            int clave = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese clave a eliminar:"));
            control.quitarPorClave(clave);
            JOptionPane.showMessageDialog(this, "Instrumento eliminado (si existía).");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al eliminar.");
        }
    }

    private String convertirListaAString(ArrayList<Instrumento> lista) {
        StringBuilder sb = new StringBuilder();
        for (Instrumento inst : lista) {
            sb.append(inst.toString()).append("\n");
        }
        return sb.toString();
    }
}
