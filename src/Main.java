import controller.MenuController;
import view.game.ViewMenu;

/**
 * Classe principal para iniciar a aplicação.
 * 
 * @author gbrsnts
 */
public class Main {
    public static void main(String[] args) {
        // Instancia o controlador principal do sistema
        MenuController controller = new MenuController();
        
        // Instancia a View de Menu
        ViewMenu menu = new ViewMenu(controller);
        
        // Inicia a execução da aplicação
        menu.iniciar();
    }
}