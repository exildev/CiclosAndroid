package co.com.script.ciclos.models;

/**
 * Created by pico on 07/11/2016.
 */

public class Asignacion {
    private int id;
    private int piscina_id;
    private String nombre;
    private String cliente;
    private int orden;
    private boolean haveGPS;
    private int tienda;
    private int supervisor;

    public Asignacion(int id, int piscina_id, String nombre, String cliente, int orden, boolean haveGPS, int tienda, int supervisor) {
        this.id = id;
        this.piscina_id = piscina_id;
        this.nombre = nombre;
        this.cliente = cliente;
        this.orden = orden;
        this.haveGPS = haveGPS;
        this.tienda = tienda;
        this.supervisor = supervisor;
    }

    public int getId() {
        return id;
    }

    public int getPiscina_id() {
        return piscina_id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCliente() {
        return cliente;
    }

    public int getOrden() {
        return orden;
    }

    public boolean isHaveGPS() {
        return haveGPS;
    }

    public int getTienda() {
        return tienda;
    }

    public int getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(int supervisor) {
        this.supervisor = supervisor;
    }

    @Override
    public String toString() {
        return "Asignacion{" +
                "id=" + id +
                ", piscina_id=" + piscina_id +
                ", nombre='" + nombre + '\'' +
                ", cliente='" + cliente + '\'' +
                ", orden=" + orden +
                ", haveGPS=" + haveGPS +
                ", tienda=" + tienda +
                '}';
    }
}
