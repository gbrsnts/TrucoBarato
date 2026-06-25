package view.game;
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
}
