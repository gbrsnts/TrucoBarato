package view.game;

import model.jogador.Jogador;

/**
 *
 * @author gbrsnts
 */
public class ViewPartida {
    public static void fim(Jogador vencedor, Jogador humano){
        if (vencedor == humano) {
                System.out.println(vencedor.getNome() + " venceu a partida. Parabens!");
        } else {
            System.out.println("Voce perdeu a partida " + humano.getNome() + ". Jogue melhor na proxima vez.");
        }
    }
}
