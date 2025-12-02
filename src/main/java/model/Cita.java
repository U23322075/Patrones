package model;

import java.sql.Date;

public class Cita {

    private int id;
    private Date fecha;
    private int idMascota;
    private int idVeterinario;
    private int idMedicamento;

    public Cita() {
    }

    public Cita(Date fecha, int idMascota, int idVeterinario, int idMedicamento) {
        this.fecha = fecha;
        this.idMascota = idMascota;
        this.idVeterinario = idVeterinario;
        this.idMedicamento = idMedicamento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public int getIdVeterinario() {
        return idVeterinario;
    }

    public void setIdVeterinario(int idVeterinario) {
        this.idVeterinario = idVeterinario;
    }

    public int getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(int idMedicamento) {
        this.idMedicamento = idMedicamento;
    }
}
