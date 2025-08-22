import java.io.IOException;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Control {
    private ArrayList<Instrumento> listaInstrumentos = new ArrayList<>();
    private HashSet<String> autores = new HashSet<>();
    private final String archivo = "Archivoinstrumentos.csv";

    public Control() {
        cargarDesdeArchivo();
    }

    public String mostrarTodos() {
        if (listaInstrumentos.isEmpty()) {
            return "No hay instrumentos registrados.";
        }
        StringBuilder sb = new StringBuilder();
        for (var inst : listaInstrumentos) {
            sb.append(inst.toString()).append("\n");
        }
        return sb.toString();
    }

    public String buscarPorAutor(String nombreAutor) {
        String resultado = "";
        for (Instrumento inst : listaInstrumentos) {
            if (nombreAutor.equals(inst.getAutor())) {
                resultado += inst.toString();
            }
        }
        return resultado;
    }

    public String buscarPorEvaluacion(boolean esEvaluacion) {
        StringBuilder sb = new StringBuilder();
        for (Instrumento inst : listaInstrumentos) {
            if (inst.isEvaluacion() == esEvaluacion) {
                sb.append(inst.toString());
            }
        }
        return sb.toString();
    }

    public String buscarPorTipo(String tipo) {
        String texto = "";
        for (Instrumento inst : listaInstrumentos) {
            if (tipo.equals(inst.getTipo())) {
                texto += inst.toString();
            }
        }
        return texto;
    }

    public String buscarPorCondicion(String condicion) {
        StringBuilder sb = new StringBuilder();
        for (Instrumento inst : listaInstrumentos) {
            if (inst.getTipoCondicion().equals(condicion)) {
                sb.append(inst.toString());
            }
        }
        return sb.toString();
    }

    public String buscarPorTipoEvaluacion(String tipoEvaluacion) {
        String texto = "";
        for (Instrumento inst : listaInstrumentos) {
            if (tipoEvaluacion.equals(inst.getTipo())) {
                texto += inst.toString();
            }
        }
        return texto;
    }

    public String obtenerPorClave(int clave) {
        for (Instrumento inst : listaInstrumentos) {
            if (inst.getClave() == clave) {
                return inst.toString();
            }
        }
        return null;
    }

    public String quitarPorClave(int clave) {
        String salida = "";
        listaInstrumentos.removeIf(inst -> inst.getClave() == clave);
        for (Instrumento inst : listaInstrumentos) {
            if (inst.getClave() == clave) {
                salida += inst.toString();
            }
        }
        return salida;
    }

    public ArrayList<Instrumento> ordenarPorClave() {
        return listaInstrumentos.stream()
                .sorted(Comparator.comparingInt(Instrumento::getClave))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Instrumento> ordenarPorPrimerAutor() {
        ArrayList<Instrumento> ordenados = new ArrayList<>();
        for (String a : autores) {
            for (Instrumento inst : listaInstrumentos) {
                if (a.equals(inst.getAutor())) {
                    ordenados.add(inst);
                }
            }
        }
        return ordenados;
    }

    public boolean existeClave(int clave) {
        for (Instrumento inst : listaInstrumentos) {
            if (inst.getClave() == clave) {
                return true;
            }
        }
        return false;
    }

    public void registrarAutor(String autor) {
        autores.add(autor);
    }

    public void agregarInstrumento(Instrumento inst) {
        listaInstrumentos.add(inst);
        guardarInstrumentosEnArchivo();
    }

    public void guardarInstrumentosEnArchivo() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
            for (Instrumento inst : listaInstrumentos) {
                // Guardar en formato: clave,nombre,autor,tipo,proposito,condicion,evaluacion
                pw.println(inst.getClave() + "," +
                        inst.getNombre() + "," +
                        inst.getAutor() + "," +
                        inst.getTipo() + "," +
                        inst.getProposito() + "," +
                        inst.getTipoCondicion() + "," +
                        inst.isEvaluacion());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar instrumentos: " + e.getMessage());
        }
    }

    private void cargarDesdeArchivo() {
        File f = new File(archivo);
        if (!f.exists()) return; // No hay archivo todavía

        try (Scanner sc = new Scanner(f)) {
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                String[] partes = linea.split(",");
                if (partes.length == 7) {
                    int clave = Integer.parseInt(partes[0]);
                    String nombre = partes[1];
                    String autor = partes[2];
                    String tipo = partes[3];
                    String proposito = partes[4];
                    String condicion = partes[5];
                    boolean evaluacion = Boolean.parseBoolean(partes[6]);
                    Instrumento inst = new Instrumento(clave, nombre, autor, tipo, proposito, condicion);
                    inst.setEvaluacion(evaluacion);
                    listaInstrumentos.add(inst);
                    registrarAutor(autor);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar instrumentos: " + e.getMessage());
        }
    }
}
