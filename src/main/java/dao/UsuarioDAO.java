package dao;

import model.Usuario;

public interface UsuarioDAO {

    Usuario login(String username, String password);
}
