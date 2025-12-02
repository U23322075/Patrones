package service;

import dao.VeterinarioDAO;
import dao.factory.DAOFactory;
import model.Veterinario;
import java.util.List;

public class VeterinarioService {

    private final VeterinarioDAO veterinarioDAO = DAOFactory.getVeterinarioDAO();

    public void registrar(String nombre, String especialidad) {
        veterinarioDAO.registrar(new Veterinario(nombre, especialidad));
    }

    public List<Veterinario> listar() {
        return veterinarioDAO.listar();
    }
}
