package model;

public class Mascota {

    private int id;
    private String nombre;
    private String especie;
    private int idCliente;

    public Mascota() {
    }

    public Mascota(String nombre, String especie, int idCliente) {
        this.nombre = nombre;
        this.especie = especie;
        this.idCliente = idCliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
}
