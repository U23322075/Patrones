package dao;

import model.Cliente;
import java.util.List;

public interface ClienteDAO {

    void registrar(Cliente c);

    List<Cliente> listar();
}
