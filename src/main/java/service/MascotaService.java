package service;

import dao.MascotaDAO;
import dao.factory.DAOFactory;
import model.Mascota;
import java.util.List;

public class MascotaService {

    private final MascotaDAO mascotaDAO = DAOFactory.getMascotaDAO();

    public void registrar(String nombre, String especie, int idCliente) {
        mascotaDAO.registrar(new Mascota(nombre, especie, idCliente));
    }

    public List<Mascota> listar() {
        return mascotaDAO.listar();
    }
}
