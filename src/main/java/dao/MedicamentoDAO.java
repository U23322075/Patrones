package dao;

import model.Medicamento;
import java.util.List;

public interface MedicamentoDAO {

    void registrar(Medicamento m);

    List<Medicamento> listar();
}
