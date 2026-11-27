package view.game;
import model.carta.Carta;
import model.jogador.Jogador;
import view.util.Input;
import java.util.stream.IntStream;

/**
 * Classe responsável pelos outputs da classe "turno".
 * 
 * @author gbrsnts
 */
public class ViewTurno {
    // Valor devolvido por escolherCarta quando o jogador decide desistir.
    public static final int DESISTIR = -1;
    
    // Exibe a carta escolhida pelo jogador.
    public static void mostrarJogada(Jogador jogador, Carta carta) {
        System.out.println(jogador.getNome() + " jogou um " + carta);
    }
    
    // Exibe todas cartas ainda disponíveis na mão do jogador
    public static void mostrarMao(Jogador jogador){
        IntStream.range(0, jogador.getMao().size())
                .forEach(i -> System.out.println((i + 1) + " - " + jogador.getMao().get(i)));
    }
    
    // Permite que o jogador escolha uma carta e valida a oppção escolhida
    public static int escolherCarta(Jogador jogador) {
        while (true) {
            mostrarMao(jogador);            
            System.out.println("0 - Desistir da partida");
            int escolha = Input.lerInt("Escolha uma carta: ");
            
            if(escolha == 0){
                char confirma = Input.lerChar("Deseja realmente desistir? [S/N] ");
                if (confirma == 's'){
                    return DESISTIR;
                }
                continue;
            }
            
            if (escolha >= 1 && escolha <= jogador.getMao().size()) {
                return escolha - 1;
            }
            
            System.out.println("Opcao invalida.");
        }
    }
}
