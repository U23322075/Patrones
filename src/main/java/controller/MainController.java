package controller;

import service.*;
import java.sql.Date;
import java.util.Scanner;

public class MainController {

    private final UsuarioService usuarioService = new UsuarioService();
    private final ClienteService clienteService = new ClienteService();
    private final VeterinarioService vetService = new VeterinarioService();
    private final MascotaService mascotaService = new MascotaService();
    private final MedicamentoService medService = new MedicamentoService();
    private final CitaService citaService = new CitaService();

    public void iniciar() {
        Scanner sc = new Scanner(System.in);

        mostrarTitulo("LOGIN ADMINISTRADOR");

        while (!login(sc)) {
            mostrarError("Credenciales incorrectas. Intente nuevamente.");
        }

        mostrarTitulo("BIENVENIDO ADMINISTRADOR");

        boolean salir = false;

        while (!salir) {

            mostrarTitulo("VETERINARIA PATITAS - MENÚ PRINCIPAL");

            System.out.println("""
                1. Registrar Cliente
                2. Listar Clientes
                3. Registrar Veterinario
                4. Listar Veterinarios
                5. Registrar Medicamento
                6. Listar Medicamentos
                7. Registrar Mascota
                8. Listar Mascotas
                9. Registrar Cita
                10. Listar Citas
                11. Salir
                """);

            System.out.print("Seleccione opción: ");
            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1 ->
                    registrarCliente(sc);
                case 2 ->
                    listarClientes();
                case 3 ->
                    registrarVeterinario(sc);
                case 4 ->
                    listarVeterinarios();
                case 5 ->
                    registrarMedicamento(sc);
                case 6 ->
                    listarMedicamentos();
                case 7 ->
                    registrarMascota(sc);
                case 8 ->
                    listarMascotas();
                case 9 ->
                    registrarCita(sc);
                case 10 ->
                    listarCitas();
                case 11 ->
                    salir = true;
                default ->
                    mostrarError("Opción inválida. Intente nuevamente.");
            }
        }
    }

    private boolean login(Scanner sc) {
        System.out.print("Usuario: ");
        String user = sc.nextLine();

        System.out.print("Contraseña: ");
        String pass = sc.nextLine();

        return usuarioService.login(user, pass);
    }

    private void mostrarTitulo(String titulo) {
        String line = "=".repeat(65);
        System.out.println("\n" + line);
        System.out.println(centerText(titulo, 65));
        System.out.println(line);
    }

    private void mostrarError(String msg) {
        System.out.println("\n[✖ ERROR] " + msg + "\n");
    }

    private void mostrarOk(String msg) {
        System.out.println("[✔ OK] " + msg + "\n");
    }

    private String centerText(String text, int width) {
        int padding = (width - text.length()) / 2;
        return " ".repeat(Math.max(0, padding)) + text;
    }

    private void registrarCliente(Scanner sc) {
        mostrarTitulo("REGISTRAR CLIENTE");

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Teléfono: ");
        String tel = sc.nextLine();

        clienteService.registrar(nombre, tel);
        mostrarOk("Cliente registrado.");
    }

    private void listarClientes() {
        mostrarTitulo("LISTA DE CLIENTES");

        String header = "+------+---------------------------+------------------+";
        System.out.println(header);

        System.out.printf("| %-4s | %-25s | %-16s |\n", "ID", "Nombre", "Teléfono");
        System.out.println(header);

        clienteService.listar().forEach(c
                -> System.out.printf("| %-4d | %-25s | %-16s |\n",
                        c.getId(), c.getNombre(), c.getTelefono())
        );

        System.out.println(header);
    }

    private void registrarVeterinario(Scanner sc) {
        mostrarTitulo("REGISTRAR VETERINARIO");

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Especialidad: ");
        String esp = sc.nextLine();

        vetService.registrar(nombre, esp);
        mostrarOk("Veterinario registrado.");
    }

    private void listarVeterinarios() {
        mostrarTitulo("LISTA DE VETERINARIOS");

        String header = "+------+---------------------------+---------------------------+";
        System.out.println(header);
        System.out.printf("| %-4s | %-25s | %-25s |\n", "ID", "Nombre", "Especialidad");
        System.out.println(header);

        vetService.listar().forEach(v
                -> System.out.printf("| %-4d | %-25s | %-25s |\n",
                        v.getId(), v.getNombre(), v.getEspecialidad())
        );

        System.out.println(header);
    }

    private void registrarMedicamento(Scanner sc) {
        mostrarTitulo("REGISTRAR MEDICAMENTO");

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Descripción: ");
        String desc = sc.nextLine();

        medService.registrar(nombre, desc);
        mostrarOk("Medicamento registrado.");
    }

    private void listarMedicamentos() {
        mostrarTitulo("LISTA DE MEDICAMENTOS");

        String header = "+------+---------------------------+----------------------------------------------+";
        System.out.println(header);
        System.out.printf("| %-4s | %-25s | %-44s |\n", "ID", "Nombre", "Descripción");
        System.out.println(header);

        medService.listar().forEach(m
                -> System.out.printf("| %-4d | %-25s | %-44s |\n",
                        m.getId(), m.getNombre(), m.getDescripcion())
        );

        System.out.println(header);
    }

    private void registrarMascota(Scanner sc) {
        mostrarTitulo("REGISTRAR MASCOTA");

        System.out.print("Nombre mascota: ");
        String nombre = sc.nextLine();
        System.out.print("Especie: ");
        String especie = sc.nextLine();
        System.out.print("ID Cliente: ");
        int idCliente = sc.nextInt();
        sc.nextLine();

        mascotaService.registrar(nombre, especie, idCliente);
        mostrarOk("Mascota registrada.");
    }

    private void listarMascotas() {
        mostrarTitulo("LISTA DE MASCOTAS");

        String header = "+------+----------------------+----------------+-------------+";
        System.out.println(header);
        System.out.printf("| %-4s | %-20s | %-14s | %-11s |\n",
                "ID", "Nombre", "Especie", "ID Cliente");
        System.out.println(header);

        mascotaService.listar().forEach(m
                -> System.out.printf("| %-4d | %-20s | %-14s | %-11d |\n",
                        m.getId(), m.getNombre(), m.getEspecie(), m.getIdCliente())
        );

        System.out.println(header);
    }

    private void registrarCita(Scanner sc) {
        mostrarTitulo("REGISTRAR CITA");

        System.out.print("Fecha (YYYY-MM-DD): ");
        Date fecha = Date.valueOf(sc.nextLine());
        System.out.print("ID Mascota: ");
        int idMascota = sc.nextInt();
        System.out.print("ID Veterinario: ");
        int idVet = sc.nextInt();
        System.out.print("ID Medicamento: ");
        int idMed = sc.nextInt();
        sc.nextLine();

        citaService.registrar(fecha, idMascota, idVet, idMed);
        mostrarOk("Cita registrada.");
    }

    private void listarCitas() {
        mostrarTitulo("LISTA DE CITAS");

        String header = "+------+------------+-----------+--------------+--------------+";
        System.out.println(header);
        System.out.printf("| %-4s | %-10s | %-9s | %-12s | %-12s |\n",
                "ID", "Fecha", "Mascota", "Veterinario", "Medicamento");
        System.out.println(header);

        citaService.listar().forEach(c
                -> System.out.printf("| %-4d | %-10s | %-9d | %-12d | %-12d |\n",
                        c.getId(), c.getFecha(), c.getIdMascota(), c.getIdVeterinario(), c.getIdMedicamento())
        );

        System.out.println(header);
    }
}
