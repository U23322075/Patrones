package service;

import dao.MedicamentoDAO;
import dao.factory.DAOFactory;
import model.Medicamento;
import java.util.List;

public class MedicamentoService {

    private final MedicamentoDAO medicamentoDAO = DAOFactory.getMedicamentoDAO();

    public void registrar(String nombre, String descripcion) {
        medicamentoDAO.registrar(new Medicamento(nombre, descripcion));
    }

    public List<Medicamento> listar() {
        return medicamentoDAO.listar();
    }
}
