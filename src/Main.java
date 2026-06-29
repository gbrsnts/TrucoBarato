import controller.MenuController;
import view.Menu;

/**
 * Classe principal para iniciar a aplicação.
 * 
 * @author gbrsnts
 */
public class Main {

    public static void main(String[] args) {
        // Instancia o controlador principal do sistema
        MenuController controller = new MenuController();
        
        // Instancia a interface de menu
        Menu menu = new Menu(controller);
        
        // Inicia a execução da aplicação
        menu.iniciar();
    }
}