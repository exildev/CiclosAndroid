package co.com.script.ciclos.models;


public class Planilla {
    private Boolean espera;
    private String nombreT;
    private String nombreP;
    private int campana;
    private int supervisor_id;
    private Integer planilla;
    private Boolean salida;
    private Integer orden;
    private Integer id;
    private Double latitud;
    private Double longitud;

    public Planilla(Boolean espera, String nombreT, String nombreP, int campana, int supervisor_id, Integer planilla, Boolean salida, Integer orden, Integer id, Double latitud, Double longitud) {
        this.espera = espera;
        this.nombreT = nombreT;
        this.nombreP = nombreP;
        this.campana = campana;
        this.supervisor_id = supervisor_id;
        this.planilla = planilla;
        this.salida = salida;
        this.orden = orden;
        this.id = id;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Boolean getEspera() {
        return espera;
    }

    public String getNombreT() {
        return nombreT;
    }

    public String getNombreP() {
        return nombreP;
    }

    public int getCampana() {
        return campana;
    }

    public int getSupervisor_id() {
        return supervisor_id;
    }

    public Integer getPlanilla() {
        return planilla;
    }

    public Boolean getSalida() {
        return salida;
    }

    public Integer getOrden() {
        return orden;
    }

    public Integer getId() {
        return id;
    }

    public Double getLatitud() {
        return latitud;
    }

    public Double getLongitud() {
        return longitud;
    }
}
