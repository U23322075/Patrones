package dao.factory;

import dao.*;

public class DAOFactory {

    public static ClienteDAO getClienteDAO() {
        return new ClienteDAOImpl();
    }

    public static VeterinarioDAO getVeterinarioDAO() {
        return new VeterinarioDAOImpl();
    }

    public static MascotaDAO getMascotaDAO() {
        return new MascotaDAOImpl();
    }

    public static MedicamentoDAO getMedicamentoDAO() {
        return new MedicamentoDAOImpl();
    }

    public static CitaDAO getCitaDAO() {
        return new CitaDAOImpl();
    }

    public static UsuarioDAO getUsuarioDAO() {
        return new UsuarioDAOImpl();
    }

}
