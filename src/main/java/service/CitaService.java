package service;

import dao.CitaDAO;
import dao.factory.DAOFactory;
import model.Cita;

import java.sql.Date;
import java.util.List;

public class CitaService {

    private final CitaDAO citaDAO = DAOFactory.getCitaDAO();

    public void registrar(Date fecha, int idMascota, int idVet, int idMed) {
        citaDAO.registrar(new Cita(fecha, idMascota, idVet, idMed));
    }

    public List<Cita> listar() {
        return citaDAO.listar();
    }
}
