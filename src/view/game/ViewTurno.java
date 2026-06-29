package view.game;
import java.util.InputMismatchException;
import java.util.Scanner;
import model.carta.Carta;
import model.jogador.Jogador;
import view.util.Input;

/**
 * Classe responsável pelos outputs da classe "turno".
 * 
 * @author gbrsnts
 */
public class ViewTurno {
    
    // Exibe a carta escolhida pelo jogador.
    public static void mostrarJogada(Jogador jogador, Carta carta) {
        System.out.println(jogador.getNome() + " jogou um " + carta);
    }
    
    // Exibe todas cartas ainda disponíveis na mão do jogador
    public static void mostrarMao(Jogador jogador){
        for (int i = 0; i < jogador.getMao().size(); i++) {
            System.out.println((i + 1) + " - " + jogador.getMao().get(i));
        }
    }
    
    // Permite que o jogador escolha uma carta e valida a oppção escolhida
    public static int escolherCarta(Jogador jogador) {
        while (true) {
            mostrarMao(jogador);            
            int escolha = Input.lerInt("Escolha uma carta: ");
            
            if (escolha >= 1 && escolha <= jogador.getMao().size()) {
                return escolha - 1;
            }
            
            System.out.println("Opcao invalida.");
        }
    }
}
