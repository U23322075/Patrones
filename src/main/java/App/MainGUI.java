package App;

import service.*;
import model.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.Date;

public class MainGUI extends JFrame {

    private final UsuarioService usuarioService = new UsuarioService();
    private final ClienteService clienteService = new ClienteService();
    private final VeterinarioService vetService = new VeterinarioService();
    private final MascotaService mascotaService = new MascotaService();
    private final MedicamentoService medService = new MedicamentoService();
    private final CitaService citaService = new CitaService();

    private JTabbedPane tabbedPane;

    private final Color PRIMARY = Color.decode("#2196F3");
    private final Color PRIMARY_DARK = Color.decode("#1976D2");
    private final Color PRIMARY_LIGHT = Color.decode("#BBDEFB");
    private final Color ROW_ALT = Color.decode("#E3F2FD");
    private final Color CARD_BG = Color.WHITE;
    private final Font FONT_TITLE = new Font("Segoe UI", Font.BOLD, 16);

    public MainGUI() {
        setTitle("Veterinaria Patitas");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        if (!loginDialog()) {
            System.exit(0);
        }

        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tabbedPane.setBackground(PRIMARY);
        tabbedPane.setForeground(Color.WHITE);
        tabbedPane.setBorder(new EmptyBorder(10, 10, 10, 10));

        add(tabbedPane, BorderLayout.CENTER);

        tabbedPane.addTab("Clientes", crearPanelClientes());
        tabbedPane.addTab("Veterinarios", crearPanelVeterinarios());
        tabbedPane.addTab("Medicamentos", crearPanelMedicamentos());
        tabbedPane.addTab("Mascotas", crearPanelMascotas());
        tabbedPane.addTab("Citas", crearPanelCitas());
    }

    private boolean loginDialog() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.setPreferredSize(new Dimension(300, 70));

        JTextField userField = new JTextField();
        JPasswordField passField = new JPasswordField();

        panel.add(new JLabel("Usuario:"));
        panel.add(userField);
        panel.add(new JLabel("Contraseña:"));
        panel.add(passField);

        JPanel container = new JPanel(new BorderLayout());
        container.add(panel, BorderLayout.CENTER);
        container.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(PRIMARY_DARK, 2, true),
                new EmptyBorder(20, 20, 20, 20)
        ));
        container.setBackground(CARD_BG);

        int option = JOptionPane.showConfirmDialog(
                this, container, "Login Administrador",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            return usuarioService.login(userField.getText(), new String(passField.getPassword()))
                    || loginDialog();
        }
        return false;
    }

    private JPanel crearCardPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(CARD_BG);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15),
                BorderFactory.createLineBorder(PRIMARY_LIGHT, 2, true)
        ));
        return panel;
    }

    private JButton crearBoton(String texto, Color color) {
        JButton b = new JButton(texto);
        b.setFont(FONT_TITLE);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        b.setBackground(color);
        b.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b.setBackground(color.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                b.setBackground(color);
            }
        });
        return b;
    }

    private JTable crearTablaProfesional(DefaultTableModel modelo) {
        JTable tabla = new JTable(modelo);
        tabla.setRowHeight(28);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabla.setFillsViewportHeight(true);

        // Encabezado
        JTableHeader header = tabla.getTableHeader();
        header.setBackground(PRIMARY_DARK);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 15));

        tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int col) {
                Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, col);
                if (isSelected) {
                    c.setBackground(PRIMARY_LIGHT);
                    c.setForeground(Color.BLACK);
                } else {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : ROW_ALT);
                    c.setForeground(Color.BLACK);
                }
                return c;
            }
        });

        tabla.setGridColor(Color.LIGHT_GRAY);
        return tabla;
    }

    private JPanel crearPanelClientes() {
        JPanel panel = crearCardPanel();
        DefaultTableModel modelo = new DefaultTableModel(
                new String[]{"ID", "Nombre", "Teléfono"}, 0);
        JTable tabla = crearTablaProfesional(modelo);
        refrescarClientes(modelo);

        JButton btnAgregar = crearBoton("Registrar Cliente", Color.decode("#42A5F5"));
        btnAgregar.addActionListener(e -> {
            String nombre = JOptionPane.showInputDialog(this, "Nombre:");
            String tel = JOptionPane.showInputDialog(this, "Teléfono:");
            if (nombre != null && tel != null) {
                clienteService.registrar(nombre, tel);
                refrescarClientes(modelo);
            }
        });

        panel.add(new JScrollPane(tabla), BorderLayout.CENTER);
        panel.add(btnAgregar, BorderLayout.SOUTH);
        return panel;
    }

    private void refrescarClientes(DefaultTableModel modelo) {
        modelo.setRowCount(0);
        clienteService.listar().forEach(c
                -> modelo.addRow(new Object[]{c.getId(), c.getNombre(), c.getTelefono()})
        );
    }

    private JPanel crearPanelVeterinarios() {
        JPanel panel = crearCardPanel();
        DefaultTableModel modelo = new DefaultTableModel(
                new String[]{"ID", "Nombre", "Especialidad"}, 0);
        JTable tabla = crearTablaProfesional(modelo);
        refrescarVeterinarios(modelo);

        JButton btnAgregar = crearBoton("Registrar Veterinario", Color.decode("#64B5F6"));
        btnAgregar.addActionListener(e -> {
            String nombre = JOptionPane.showInputDialog(this, "Nombre:");
            String esp = JOptionPane.showInputDialog(this, "Especialidad:");
            if (nombre != null && esp != null) {
                vetService.registrar(nombre, esp);
                refrescarVeterinarios(modelo);
            }
        });

        panel.add(new JScrollPane(tabla), BorderLayout.CENTER);
        panel.add(btnAgregar, BorderLayout.SOUTH);
        return panel;
    }

    private void refrescarVeterinarios(DefaultTableModel modelo) {
        modelo.setRowCount(0);
        vetService.listar().forEach(v
                -> modelo.addRow(new Object[]{v.getId(), v.getNombre(), v.getEspecialidad()})
        );
    }

    private JPanel crearPanelMedicamentos() {
        JPanel panel = crearCardPanel();
        DefaultTableModel modelo = new DefaultTableModel(
                new String[]{"ID", "Nombre", "Descripción"}, 0);
        JTable tabla = crearTablaProfesional(modelo);
        refrescarMedicamentos(modelo);

        JButton btnAgregar = crearBoton("Registrar Medicamento", Color.decode("#4FC3F7"));
        btnAgregar.addActionListener(e -> {
            String nombre = JOptionPane.showInputDialog(this, "Nombre:");
            String desc = JOptionPane.showInputDialog(this, "Descripción:");
            if (nombre != null && desc != null) {
                medService.registrar(nombre, desc);
                refrescarMedicamentos(modelo);
            }
        });

        panel.add(new JScrollPane(tabla), BorderLayout.CENTER);
        panel.add(btnAgregar, BorderLayout.SOUTH);
        return panel;
    }

    private void refrescarMedicamentos(DefaultTableModel modelo) {
        modelo.setRowCount(0);
        medService.listar().forEach(m
                -> modelo.addRow(new Object[]{m.getId(), m.getNombre(), m.getDescripcion()})
        );
    }

    private JPanel crearPanelMascotas() {
        JPanel panel = crearCardPanel();
        DefaultTableModel modelo = new DefaultTableModel(
                new String[]{"ID", "Nombre", "Especie", "ID Cliente"}, 0);
        JTable tabla = crearTablaProfesional(modelo);
        refrescarMascotas(modelo);

        JButton btnAgregar = crearBoton("Registrar Mascota", Color.decode("#64B5F6"));
        btnAgregar.addActionListener(e -> {
            String nombre = JOptionPane.showInputDialog(this, "Nombre:");
            String especie = JOptionPane.showInputDialog(this, "Especie:");
            String idCliente = JOptionPane.showInputDialog(this, "ID Cliente:");
            if (nombre != null && especie != null && idCliente != null) {
                mascotaService.registrar(nombre, especie, Integer.parseInt(idCliente));
                refrescarMascotas(modelo);
            }
        });

        panel.add(new JScrollPane(tabla), BorderLayout.CENTER);
        panel.add(btnAgregar, BorderLayout.SOUTH);
        return panel;
    }

    private void refrescarMascotas(DefaultTableModel modelo) {
        modelo.setRowCount(0);
        mascotaService.listar().forEach(m
                -> modelo.addRow(new Object[]{m.getId(), m.getNombre(), m.getEspecie(), m.getIdCliente()})
        );
    }

    private JPanel crearPanelCitas() {
        JPanel panel = crearCardPanel();
        DefaultTableModel modelo = new DefaultTableModel(
                new String[]{"ID", "Fecha", "Mascota", "Veterinario", "Medicamento"}, 0);
        JTable tabla = crearTablaProfesional(modelo);
        refrescarCitas(modelo);

        JButton btnAgregar = crearBoton("Registrar Cita", Color.decode("#42A5F5"));
        btnAgregar.addActionListener(e -> {
            String fechaStr = JOptionPane.showInputDialog(this, "Fecha (YYYY-MM-DD):");
            String idMascotaStr = JOptionPane.showInputDialog(this, "ID Mascota:");
            String idVetStr = JOptionPane.showInputDialog(this, "ID Veterinario:");
            String idMedStr = JOptionPane.showInputDialog(this, "ID Medicamento:");

            if (fechaStr != null && idMascotaStr != null
                    && idVetStr != null && idMedStr != null) {

                Date fecha = Date.valueOf(fechaStr);
                int idMascota = Integer.parseInt(idMascotaStr);
                int idVet = Integer.parseInt(idVetStr);
                int idMed = Integer.parseInt(idMedStr);

                citaService.registrar(fecha, idMascota, idVet, idMed);
                refrescarCitas(modelo);
            }
        });

        panel.add(new JScrollPane(tabla), BorderLayout.CENTER);
        panel.add(btnAgregar, BorderLayout.SOUTH);
        return panel;
    }

    private void refrescarCitas(DefaultTableModel modelo) {
        modelo.setRowCount(0);
        citaService.listar().forEach(c
                -> modelo.addRow(new Object[]{
            c.getId(), c.getFecha(), c.getIdMascota(),
            c.getIdVeterinario(), c.getIdMedicamento()
        })
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainGUI().setVisible(true));
    }
}
