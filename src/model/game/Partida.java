package model.game;

import model.jogador.Jogador;
import model.jogador.JogadorHumano;
import model.jogador.JogadorMaquina;
import view.game.ViewPartida;

/**
 * Classe responsável pela lógica principal do jogo.
 * 
 * @author gbrsnts
 */
public class Partida {
    private JogadorHumano jogadorHumano;
    private JogadorMaquina jogadorMaquina;
    private static final int PONTOS_PARA_VENCER = 5;
    private int numeroRodada = 1;
    
    public Partida(JogadorHumano jogadorHumano, JogadorMaquina jogadorMaquina) {
        // RELACIONAMENTO: COMPOSIÇÃO
        // Os jogadores são instanciados dentro da "Partida.
        // E fazem parte do ciclo de vida da "Partida".
        this.jogadorHumano = jogadorHumano;
        this.jogadorMaquina = jogadorMaquina;
    }
    
    public String iniciarPartida(){
        /* Loop que continua a partida enquanto nenhum jogador atingir 
        a pontuação necessária para a rodada acabar. */
        while (jogadorHumano.getRoundPoints() < PONTOS_PARA_VENCER &&
                jogadorMaquina.getRoundPoints() < PONTOS_PARA_VENCER){
            
            Jogador primeiro;
            Jogador segundo;

            /* Define quem começa a rodada, caso a rodada seja ímpar
            será a vez do jogadorHumano, caso contrário, será a máquina. */
            if(numeroRodada % 2 != 0) {
                primeiro = jogadorHumano;
                segundo = jogadorMaquina;
            } else {
                primeiro = jogadorMaquina;
                segundo = jogadorHumano;
            }
            
            // Exibe o cabeçalho da rodada para o usuário.
            ViewPartida.cabecalho(numeroRodada, jogadorHumano, jogadorMaquina);
            
            // Cria uma nova rodada com ordem definida.
            Rodada rodada = new Rodada(primeiro, segundo);
            
            // Executa a rodada e captura o vencedor.
            Jogador vencedorRodada = rodada.iniciarRodada();
            
            // Atualiza a pontuação do vencedor da rodada.
            vencedorRodada.ganhouRodada();
            
            // Exibe o vencedor da rodada.
            ViewPartida.ganhadorRodada(vencedorRodada);
            
            // Incrementa o contador de rodadas.
            numeroRodada++;
            
        }
        Jogador vencedor = anunciarVencedor();
        
        // Monta o placar final.
        String placar =
                jogadorHumano.getRoundPoints()
                + " x "
                + jogadorMaquina.getRoundPoints();
        
        // Exibe o resultado da partida.
        ViewPartida.fim(vencedor, jogadorHumano);
        
        // Retorna o placar pro JogoController inserir no histórico.
        return placar;
    }
    
    // Determina qual jogador venceu a partida.   
    private Jogador anunciarVencedor() {
        if (jogadorHumano.getRoundPoints() > jogadorMaquina.getRoundPoints()) {
            return jogadorHumano;
        }
        return jogadorMaquina;
    }
    
}
