package co.com.script.ciclos.models;

/**
 * @author Cristóbal Romero Rossi <https://github.com/Cromeror>
 * @version 1.0
 */

public class Campania {
    private int id;
    private String descripcion;
    private String nombre;

    public Campania(int id) {
        this.id = id;
    }

    public Campania(int id, String nombre, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
