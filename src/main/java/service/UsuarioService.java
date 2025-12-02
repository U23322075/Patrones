package service;

import dao.UsuarioDAO;
import dao.factory.DAOFactory;
import model.Usuario;

public class UsuarioService {

    private final UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();

    public boolean login(String username, String password) {
        Usuario u = usuarioDAO.login(username, password);
        return u != null;
    }
}
