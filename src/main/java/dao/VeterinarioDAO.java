package dao;

import model.Veterinario;
import java.util.List;

public interface VeterinarioDAO {

    void registrar(Veterinario v);

    List<Veterinario> listar();
}
