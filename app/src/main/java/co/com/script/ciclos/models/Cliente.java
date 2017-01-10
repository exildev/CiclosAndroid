package co.com.script.ciclos.models;


public class Cliente {

    public static final boolean ES_PROPIETARIO = true;

    private String name;
    private int id;
    private Float latitude;
    private Float longitude;
    private int number;
    private String address;

    public Cliente(String name, int id, Float latitude, Float longitude, int number, String address) {
        this.name = name;
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.number = number;
        this.address = address;
    }

    public Cliente(String name) {

        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
