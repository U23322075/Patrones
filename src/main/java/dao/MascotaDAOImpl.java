package dao;

import config.DatabaseConnection;
import model.Mascota;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MascotaDAOImpl implements MascotaDAO {

    @Override
    public void registrar(Mascota m) {
        String sql = "INSERT INTO mascota(nombre, especie, id_cliente) VALUES (?, ?, ?)";

        try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, m.getNombre());
            ps.setString(2, m.getEspecie());
            ps.setInt(3, m.getIdCliente());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Mascota> listar() {
        List<Mascota> lista = new ArrayList<>();
        String sql = "SELECT * FROM mascota";

        try (Statement st = DatabaseConnection.getConnection().createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Mascota m = new Mascota();
                m.setId(rs.getInt("id"));
                m.setNombre(rs.getString("nombre"));
                m.setEspecie(rs.getString("especie"));
                m.setIdCliente(rs.getInt("id_cliente"));
                lista.add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}
