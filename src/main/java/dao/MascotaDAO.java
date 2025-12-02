package dao;

import model.Mascota;
import java.util.List;

public interface MascotaDAO {

    void registrar(Mascota m);

    List<Mascota> listar();
}
