package service;

import dao.ClienteDAO;
import dao.factory.DAOFactory;
import model.Cliente;
import java.util.List;

public class ClienteService {

    private final ClienteDAO clienteDAO = DAOFactory.getClienteDAO();

    public void registrar(String nombre, String telefono) {
        clienteDAO.registrar(new Cliente(nombre, telefono));
    }

    public List<Cliente> listar() {
        return clienteDAO.listar();
    }
}
