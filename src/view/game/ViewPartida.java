package view.game;

import model.jogador.Jogador;
import view.util.Input;

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
    
    public static void cabecalho(int numeroRodada, 
            Jogador jogadorHumano, Jogador jogadorMaquina){
        Input.linha();
        System.out.println("RODADA " + numeroRodada);
        Input.separador();
        
        System.out.printf(
            "%s %d x %d %s%n%n",
            jogadorHumano.getNome(),
            jogadorHumano.getRoundPoints(),
            jogadorMaquina.getRoundPoints(),
            jogadorMaquina.getNome()
        );
    }
    
    public static void ganhadorRodada(Jogador jogador){
        System.out.println("\u001B[34m" 
        + jogador.getNome() 
        + " ganhou a rodada.\u001B[0m");
    }
    
}
