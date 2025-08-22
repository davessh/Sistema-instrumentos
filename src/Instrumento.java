import java.util.*;

public class Instrumento {

    private int clave;
    private String nombre;
    private String autor;
    private String tipo;
    private String proposito; //tipo (identificar o manejar)
    private String tipoCondicion;
    private boolean evaluacion;
    private String citaEvaluacion;

    //constructor instrumento
    public Instrumento(int clave, String nombre,
                       String autor, String tipo, String proposito, String tipoCondicion) {
        this.clave = clave;
        this.nombre = nombre;
        this.autor = autor;
        this.tipo = tipo;
        this.proposito = proposito;
        this.tipoCondicion = tipoCondicion;
        this.evaluacion = false;
        this.citaEvaluacion = "";
    }

    //getters y setters
    public int getClave() {
        return clave;
    }
    public void setClave(int clave) {
        this.clave = clave;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getProposito() {
        return proposito;
    }
    public void setProposito(String proposito) {
        this.proposito = proposito;
    }
    public String getTipoCondicion() {
        return tipoCondicion;
    }
    public void setTipoCondicion(String tipoCondicion) {
        this.tipoCondicion = tipoCondicion;
    }
    public boolean isEvaluacion() {
        return evaluacion;
    }
    public void setEvaluacion(boolean evaluacion) {
        this.evaluacion = evaluacion;
    }
    public String getCitaEvaluacion() {
        return citaEvaluacion;
    }
    public void setCitaEvaluacion(String citaEvaluacion) {
        this.citaEvaluacion = citaEvaluacion;
    }

    //Método toString que permite imprimir el contenido de un instrumento
    public String toString() {
        return "Autor: " + autor + "\nClave: " + clave + "\nPropósito: " + proposito + "\nCondiciones: " + tipo +
                "\nTipo de instrumento: " + tipoCondicion + "\n\n";
    }
}
