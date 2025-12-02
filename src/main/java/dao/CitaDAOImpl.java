package dao;

import config.DatabaseConnection;
import model.Cita;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitaDAOImpl implements CitaDAO {

    @Override
    public void registrar(Cita c) {
        String sql = "INSERT INTO cita(fecha, id_mascota, id_veterinario, id_medicamento) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
            ps.setDate(1, c.getFecha());
            ps.setInt(2, c.getIdMascota());
            ps.setInt(3, c.getIdVeterinario());
            ps.setInt(4, c.getIdMedicamento());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Cita> listar() {
        List<Cita> lista = new ArrayList<>();
        String sql = "SELECT * FROM cita";

        try (Statement st = DatabaseConnection.getConnection().createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Cita c = new Cita();
                c.setId(rs.getInt("id"));
                c.setFecha(rs.getDate("fecha"));
                c.setIdMascota(rs.getInt("id_mascota"));
                c.setIdVeterinario(rs.getInt("id_veterinario"));
                c.setIdMedicamento(rs.getInt("id_medicamento"));
                lista.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}
