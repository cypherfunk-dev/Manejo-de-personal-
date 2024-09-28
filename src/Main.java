import menu.Menu;

public class Main {
    public static void main(String[] args) {

        boolean continuar = true;

        Menu menu = new Menu();
        int opcion = menu.mostrarMenu();

         while (continuar) {
             if (opcion == 5){
                 continuar = false;
             } else {
                 menu.MenuPrincipal(opcion);
                 opcion = menu.mostrarMenu();
             }
         }
         }}


