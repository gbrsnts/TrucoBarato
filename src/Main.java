import controller.MenuController;
import view.Menu;

/**
 *
 * @author gbrsnts
 */
public class Main {

    public static void main(String[] args) {
        // Instancia e inicia o menu
        MenuController controller = new MenuController();
        Menu menu = new Menu(controller);
        menu.iniciar();
    }
}