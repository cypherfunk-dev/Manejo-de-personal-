package menu;
import colaboradores.Colaboradores;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Menu {

    Colaboradores.GestorColaboradores gestor = new Colaboradores.GestorColaboradores();
    private Scanner scanner = new Scanner(System.in); // Scanner declarado a nivel de clase para que funcione en la clase accion realizada
    // mejorar el algoritmo, funciona, pero no es optimo.
    private int validarSueldobase(String sueldo) {
        boolean iterar = true;
        int sueldobase  = 0;

        while (iterar) {
            try {
                sueldobase = Integer.parseInt(sueldo);
            } catch (NumberFormatException e) {
                System.out.println("El sueldo base debe ser un numero mayor a 0");
            }
            if (sueldobase > 0){
                iterar = false;

            } else {
                System.out.println("Intente nuevamente: ");
                sueldo = System.console().readLine();
            }

        }
        return sueldobase;
    }
    private static boolean validarRut(String rut) {
        boolean validacion = false;
        try {
            rut =  rut.toUpperCase();
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

            char dv = rut.charAt(rut.length() - 1);

            int m = 0, s = 1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                validacion = true;
            } else {
                System.out.println("El Rut ingresado es incorrecto");
            }

        } catch (InputMismatchException e) {
            System.out.println("El rut está incompleto o no cumple con el formato solicitado");
        } catch (NumberFormatException e ){
            System.out.println("Error, no ingresaste un RUT");
        }
        return validacion;
    }
    private static String formatearRut(String rut){
        // Se elimina cualquier punto o guion del rut
            rut = rut.replace(".", "").replace("-", "").replace("k","K");
            StringBuilder sb = new StringBuilder(rut);

            sb.insert(sb.length() - 1, '-');
            for (int i = sb.length() - 5 ; i>0; i -=3){
                sb.insert(i, '.');
            }
            return sb.toString();
        }
    public static String obtenerRutFormateado() {
        String rutFormateado = "";
        boolean rutvalido = false;
        String rut;

        while (!rutvalido) {
            System.out.println("Ingrese rut");
            rut = System.console().readLine();  // Lee el RUT ingresado
            rutvalido = validarRut(rut);        // Verifica si el RUT es válido
            if (rutvalido) {
                rutFormateado = formatearRut(rut);  // Formatea el RUT si es válido
            } else {
                System.out.println("RUT inválido, intente nuevamente.");
            }
        }

        return rutFormateado;  // Retorna el RUT formateado después del ciclo
    }
    private void accionRealizada(){
        System.out.println("Acción realizada. Pulse Enter para continuar...");
        scanner.nextLine();
    }
    private LocalDate validarFecha(String fecha) {
        // Definir el formato de fecha
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            // Parsear la fecha a LocalDate usando el formato definido
            return LocalDate.parse(fecha, formatoFecha);
        } catch (DateTimeParseException e) {
            System.out.println("Debes ingresar la fecha en el formato correcto (dd/MM/yyyy)");
        }

        return null; // Retornar null si ocurre una excepción
    }
    public Period periodoHastaHoy (LocalDate fecha){
        LocalDate ahora = LocalDate.now();
        return  Period.between(fecha, ahora);
    }
    public int mostrarMenu() {
        String separador = "*******************************************";
        System.out.println(separador);
        System.out.println("* Bienvenido, por favor ingrese su opción: *");
        System.out.println(separador);
        System.out.println("1. Ingresar Colaborador");
        System.out.println("2. Ver Colaborador");
        System.out.println("3. Desactivar/Activar Colaborador");
        System.out.println("4. Estado de la Empresa");
        System.out.println("5. Salir");

        int seleccion = 0;
        while (true) {
            System.out.print("Ingrese su opción: ");
            String input = scanner.nextLine(); // Lee la entrada como String
            try {
                seleccion = Integer.parseInt(input); // Intenta convertirla a entero
                if (seleccion >= 1 && seleccion <= 5) { // Verifica si está en el rango
                    break; // Salir del bucle si es válida
                } else {
                    System.out.println("Opción no válida, intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error, por favor ingrese un número.");
            }
        }

        return seleccion;
    }
    public void MenuPrincipal(int opcion) {

        switch (opcion){
//          Opcion para ingresar Colaborador
            case 1: {
                String rutConFormato = Menu.obtenerRutFormateado();
                String rutSinFormato = rutConFormato.replace(".", "").replace("-", "").replace("k","K");
                System.out.println("Ingrese Nombre");
                String nombre = System.console().readLine();
                System.out.println("Ingrese Genero");
                String genero = System.console().readLine();
                System.out.println("Ingrese fecha de Nacimiento en el siguiente formato Dia/Mes/Año. Ej: 02/09/1989");
                String nacimiento = System.console().readLine();
                LocalDate nacimientoParse = validarFecha(nacimiento);
                // Aplicar DRY para no repetir ciclo while
                while (nacimientoParse == null){
                    System.out.println("Ingrese fecha de Nacimiento en el siguiente formato Dia/Mes/Año. Ej: 02/09/1989");
                    nacimiento = System.console().readLine();
                    nacimientoParse = validarFecha(nacimiento);
                    if (nacimientoParse != null) {
                        break;
                    }
                }
                System.out.println("Ingrese fecha de ingreso en el siguiente formato Dia/Mes/Año. Ej: 02/09/1989");
                String ingreso = System.console().readLine();
                LocalDate ingresoParse = validarFecha(ingreso);

                while (ingresoParse == null){
                    System.out.println("Ingrese fecha de ingreso en el siguiente formato Dia/Mes/Año. Ej: 02/09/1989");
                    ingreso = System.console().readLine();
                    ingresoParse = validarFecha(ingreso);
                    if (ingresoParse != null) {
                        break;
                    }
                }
                System.out.println("Ingrese cargo");
                String cargo = System.console().readLine();
                System.out.println("Ingrese sueldo base");
                String sueldobasestring = System.console().readLine();
                int sueldobase = validarSueldobase(sueldobasestring);
                System.out.println("Ingrese departamento");
                String departamento = System.console().readLine();
                try {
                    Colaboradores Nuevo = new Colaboradores(rutSinFormato, rutConFormato,nombre, genero, nacimientoParse, ingresoParse, cargo,sueldobase, departamento);
                    gestor.agregarcolaborador(Nuevo);
                    System.out.println("Total de colaboradores: " + gestor.GetListaColaboradores().size());
                    accionRealizada();
                } catch (IllegalArgumentException e) {
                    System.out.println("Error al intentar crear colaborador, por favor complete todos los campos... ");
                }
                break;
            }
//          Opcion para consultar por Colaborador
            case 2: {
                System.out.println("Ingrese el rut del colaborador que desea consultar: ");
                Scanner scanner = new Scanner(System.in);
                String rutbusqueda = scanner.nextLine();
                rutbusqueda = rutbusqueda.replace(".", "").replace("-", "").replace("k","K");

                int indice = gestor.buscarindicecolaborador(rutbusqueda);
                if (indice != -1) {
                    Colaboradores colaborador = gestor.getColaborador(indice);
                    System.out.println("RUT [ "+colaborador.getRutformateado()+" ]");
                    System.out.println("Nombre [ "+colaborador.getNombre()+" ]");

                    Period edad = periodoHastaHoy(colaborador.getNacimiento());
                    System.out.println("Edad [ "+edad.getYears()+" años ]");
                    System.out.println("Genero [ "+colaborador.getGenero()+" ]");
                    long totalDias = ChronoUnit.DAYS.between(colaborador.getIngreso(), LocalDate.now());
                    System.out.println("Cargo [ "+ totalDias +" días en el cargo - " +colaborador.getCargo()+" ]");
                    System.out.println("Departamento [ "+colaborador.getDepartamento()+" ]");
                    int base = colaborador.getSueldobase();
                    int gratificacion = 75000;
                    int colacion = 20000;
                    int locomocion = 35000;
                    int imponible = (base+gratificacion);
                    int salud = (int) (imponible*0.07);
                    int afp = (int) (imponible*0.1);
                    int liquido = (imponible+colacion+locomocion)-salud-afp;
                    System.out.println("Haberes [ Base: $"+ base+ ", Gratificacion: $"+gratificacion+", Colacion: $"+ colacion+", Locomocion: $"+ locomocion+", Salud: $"+salud+", AFP: $"+afp+" ]");
                    System.out.println("Sueldo Liquido [ $"+ liquido +" ]");

                } else {
                    System.out.println("No existe el usuario");
                }
                accionRealizada();
                break;
            }
//          Activar/Desactivar Colaborador
            case 3: {
                System.out.println("Ingrese el rut del colaborador que desea activar/desactivar: ");
                Scanner scanner = new Scanner(System.in);
                String rutbusqueda = scanner.nextLine();
                rutbusqueda = rutbusqueda.replace(".", "").replace("-", "").replace("k","K");

                int indice = gestor.buscarindicecolaborador(rutbusqueda);
                if (indice != -1) {
                    Colaboradores colaborador = gestor.getColaborador(indice);
                    String estadoactividad = (colaborador.getEstado()) ? "El colaborador está activo" : "El colaborador esta inactivo";
                    System.out.println(estadoactividad);
                    System.out.println("¿Desea cambiar el estado del colaborador? Presione S para modificarlo o cualquier tecla para Cancelar");
                    String seleccion = System.console().readLine();
                    seleccion = seleccion.toLowerCase();
                    if (seleccion.equals("s") ){
                        colaborador.setEstado(!colaborador.getEstado());
                        accionRealizada();
                    }

                } else {
                    System.out.println("El colaborador no existe");
                }
                break;
            }
//          Estado de la empresa
            case 4: {
                System.out.println("[Colaboradores Activos]");
                ArrayList<Colaboradores> activos = gestor.GetActivos();

                for (Colaboradores c : activos ){
                    Period edad = periodoHastaHoy(c.getNacimiento());
                    System.out.println(c.getNombre()+", "+edad.getYears()+", "+c.getCargo());
                }

                System.out.println("[Colaboradores Inactivos]");
                ArrayList<Colaboradores> inactivos = gestor.GetInactivos();
                for (Colaboradores c : inactivos ){
                    Period edad = periodoHastaHoy(c.getNacimiento());
                    System.out.println(c.getNombre()+", "+edad.getYears()+", "+c.getCargo());
                }
                accionRealizada();
                break;
            }
//          Salir
            case 5: {
                System.exit(0);
            }

            default: {
                System.out.println("Opción no válida, intenta de nuevo.");
                break;
            }
        }
    }
        }