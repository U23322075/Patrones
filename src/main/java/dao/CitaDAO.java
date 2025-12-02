package dao;

import model.Cita;
import java.util.List;

public interface CitaDAO {

    void registrar(Cita c);

    List<Cita> listar();
}
