package co.com.script.ciclos.models;

/**
 * Created by pico on 7/10/2016.
 */

public class Solucion {
    private int id;
    private String descripcion;
    private String fecha;
    private String nombre;
    private String reporte;
    private String user;

    public Solucion(int id, String descripcion, String fecha, String nombre, String reporte, String user) {
        this.id = id;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.nombre = nombre;
        this.reporte = reporte;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public String getReporte() {
        return reporte;
    }

    public String getUser() {
        return user;
    }
}
