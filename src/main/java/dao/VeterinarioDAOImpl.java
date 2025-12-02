package dao;

import config.DatabaseConnection;
import model.Veterinario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeterinarioDAOImpl implements VeterinarioDAO {

    @Override
    public void registrar(Veterinario v) {
        String sql = "INSERT INTO veterinario(nombre, especialidad) VALUES (?, ?)";

        try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, v.getNombre());
            ps.setString(2, v.getEspecialidad());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Veterinario> listar() {
        List<Veterinario> lista = new ArrayList<>();
        String sql = "SELECT * FROM veterinario";

        try (Statement st = DatabaseConnection.getConnection().createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Veterinario v = new Veterinario();
                v.setId(rs.getInt("id"));
                v.setNombre(rs.getString("nombre"));
                v.setEspecialidad(rs.getString("especialidad"));
                lista.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}
