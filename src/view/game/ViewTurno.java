package view.game;
import java.util.InputMismatchException;
import java.util.Scanner;
import model.carta.Carta;
import model.jogador.Jogador;

/**
 *
 * @author gbrsnts
 */
public class ViewTurno {
    public static void mostrarJogada(Jogador jogador, Carta carta) {
        System.out.println(jogador.getNome() + " jogou um " + carta);
    }
    
    public static int escolherCarta(Jogador jogador) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            for (int i = 0; i < jogador.getMao().size(); i++) {
                System.out.println((i + 1) + " - " + jogador.getMao().get(i));
            }
            
            System.out.print("Escolha uma carta: ");
            try {
                int escolha = scanner.nextInt();

                if (escolha >= 1 && escolha <= jogador.getMao().size()) {
                    return escolha - 1;
                }

                System.out.println("Opção inválida.");

            } catch (InputMismatchException e) {
                System.out.println("Digite apenas números.");
                scanner.nextLine();
            }
        }
    }
}
