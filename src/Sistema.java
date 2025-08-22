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

        btnAgregar.addActionListener(e -> {
            try {
                int clave = Integer.parseInt(JOptionPane.showInputDialog(this, "Clave:"));
                String nombre = JOptionPane.showInputDialog(this, "Nombre:");
                String autor = JOptionPane.showInputDialog(this, "Autor:");
                String tipo = JOptionPane.showInputDialog(this, "Tipo:");
                String proposito = JOptionPane.showInputDialog(this, "Propósito:");
                String condicion = JOptionPane.showInputDialog(this, "Condición:");

                Instrumento inst = new Instrumento(clave, nombre, autor, tipo, proposito, condicion);
                control.agregarInstrumento(inst);
                control.registrarAutor(autor);

                JOptionPane.showMessageDialog(this, "Instrumento agregado con éxito.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al agregar instrumento.");
            }
        });


        btnMostrar.addActionListener(e -> {
            String datos = control.mostrarTodos();
            JOptionPane.showMessageDialog(this, datos, "Lista de Instrumentos", JOptionPane.INFORMATION_MESSAGE);
        });


        btnEliminar.addActionListener(e -> {
            try {
                int clave = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese clave a eliminar:"));
                String eliminado = control.quitarPorClave(clave);
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
