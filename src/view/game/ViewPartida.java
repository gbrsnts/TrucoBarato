package view.game;

import model.jogador.Jogador;

/**
 * Classe responsável pelos outputs da classe "partida".
 * 
 * @author gbrsnts
 */
public class ViewPartida {
    
    // Exibe a mensagem final da partida de acordo com o ganhador.
    public static void fim(Jogador vencedor, Jogador humano){
        if (vencedor == humano) {
                System.out.println(vencedor.getNome() + " venceu a partida. Parabens!");
        } else {
            System.out.println("Voce perdeu a partida " + humano.getNome() + ". Jogue melhor na proxima vez.");
        }
    }
}
