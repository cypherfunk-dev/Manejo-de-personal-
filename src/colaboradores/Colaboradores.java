package colaboradores;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Colaboradores {
    private String rut;
    private String nombre;
    private String genero;
    private LocalDate nacimiento;
    private LocalDate ingreso;
    private String cargo;
    private String departamento;
    private int sueldobase;
    private boolean estado;
    private String rutformateado;

    public String getRutformateado() {
        return rutformateado;
    }

    public void setRutformateado(String rutformateado) {
        this.rutformateado = rutformateado;
    }

    public static class GestorColaboradores {
        private ArrayList<Colaboradores> listadoColaboradores = new ArrayList<>();

        public void agregarcolaborador(Colaboradores nuevocolaborador) {
            listadoColaboradores.add(nuevocolaborador);
        }

        public int buscarindicecolaborador(String rut) {
            for (int i = 0; i < listadoColaboradores.size(); i++) {
                Colaboradores colaborador = listadoColaboradores.get(i);
                if (colaborador.getRut().equals(rut)) {
                    return i; // Retorna el índice del colaborador encontrado
                }
            }
            System.out.println("Este perfil no existe.");
            return -1; // Retorna -1 si no se encuentra el colaborador
        }

        public Colaboradores getColaborador(int index) {
            return listadoColaboradores.get(index);
        }

        public ArrayList<Colaboradores> GetListaColaboradores() {
            return listadoColaboradores;
        }
        public ArrayList<Colaboradores> GetActivos() {
            ArrayList<Colaboradores> ColaboradoresActivos = new ArrayList<>();
            for (Colaboradores c : listadoColaboradores ){
                if (c.getEstado()){
                    ColaboradoresActivos.add(c);
                }
            }
            return ColaboradoresActivos;
        }
        public ArrayList<Colaboradores> GetInactivos() {
            ArrayList<Colaboradores> ColaboradoresInactivos = new ArrayList<>();
            for (Colaboradores c : listadoColaboradores ){
                if (!c.getEstado()){
                    ColaboradoresInactivos.add(c);
                }
            }
            return ColaboradoresInactivos;
        }

    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public LocalDate getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(LocalDate nacimiento) {
        this.nacimiento = nacimiento;
    }

    public LocalDate getIngreso() {
        return ingreso;
    }

    public void setIngreso(LocalDate ingreso) {
        this.ingreso = ingreso;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public int getSueldobase() {
        return sueldobase;
    }

    public void setSueldobase(int sueldobase) {
        this.sueldobase = sueldobase;
    }

    public Colaboradores(String rut, String rutformateado,String nombre, String genero, LocalDate nacimiento, LocalDate ingreso, String cargo, int sueldobase, String departamento) {
        // Verificar tipos de datos y lanzar excepciones si es necesario
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío.");
        }
        this.rut = rut;
        this.rutformateado = rutformateado;
        this.nombre = nombre;
        this.genero = genero;
        this.nacimiento = nacimiento;
        this.ingreso = ingreso;
        this.cargo = cargo;
        this.sueldobase = sueldobase;
        this.departamento = departamento;
        this.estado = true;
        System.out.println("Usuario ingresado correctamente");
    }

}
