package model.jogo;

import model.DAO.HistoricoDAO;
import model.jogador.Jogador;
import model.jogador.JogadorHumano;
import model.jogador.JogadorMaquina;
import view.Tela;
import view.game.ViewPartida;

/**
 * Classe responsável pela lógica principal do jogo.
 * @author gbrsnts
 */
public class Partida {
    private JogadorHumano jogadorHumano;
    private JogadorMaquina jogadorMaquina;
    private static final int PONTOS_PARA_VENCER = 5;
    private int numeroRodada = 1;
    
    public Partida(JogadorHumano jogadorHumano, JogadorMaquina jogadorMaquina) {
        this.jogadorHumano = jogadorHumano;
        this.jogadorMaquina = jogadorMaquina;
    }
    
    public String iniciarPartida(){
        /** Executa rodadas até que alguém atinja 5 pontos, caso
        alguém alcance 5 pontos, a partida termina e anuncia o vencedor. */
        while (jogadorHumano.getRoundPoints() < PONTOS_PARA_VENCER &&
                jogadorMaquina.getRoundPoints() < PONTOS_PARA_VENCER){
            
            
            /** Define uma sequência de quem será o primeiro
            intercalando entre jogadores */
            
            Jogador primeiro;
            Jogador segundo;

            if(numeroRodada % 2 != 0) {
                primeiro = jogadorHumano;
                segundo = jogadorMaquina;
            } else {
                primeiro = jogadorMaquina;
                segundo = jogadorHumano;
            }
            
            Tela.cabecalho(numeroRodada, jogadorHumano, jogadorMaquina);
            
            // Instancia uma rodada a cada loop
            Rodada rodada = new Rodada(primeiro, segundo);
            
            Jogador vencedorRodada = rodada.iniciarRodada();
            vencedorRodada.ganhouRodada();
            Tela.ganhadorRodada(vencedorRodada);
            
            numeroRodada++;
            
        }
        Jogador vencedor = anunciarVencedor();
        
        String placar =
                jogadorHumano.getRoundPoints()
                + " x "
                + jogadorMaquina.getRoundPoints();
        
        ViewPartida.fim(vencedor, jogadorHumano);
        
        return placar;
    }
       
    public Jogador anunciarVencedor() {
        if (jogadorHumano.getRoundPoints() > jogadorMaquina.getRoundPoints()) {
            return jogadorHumano;
        }
        return jogadorMaquina;
    }
    
}
