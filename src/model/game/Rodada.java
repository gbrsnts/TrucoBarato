package model.game;

import model.carta.Baralho;
import model.jogador.Jogador;
import model.game.Turno;
import view.game.ViewRodada;
import view.util.Input;

/**
 * Classe responsável pela lógica de cada rodada.
 * 
 * @author gbrsnts  
 */
public class Rodada {
    private Jogador jogador1;
    private Jogador jogador2;
    private final Baralho baralho;
    private static final int TURNOS_PARA_VENCER = 2;    

    public Rodada(Jogador primeiro, Jogador segundo) {
        this.jogador1 = primeiro;
        this.jogador2 = segundo;
        this.baralho = new Baralho();
    }
        
    public Jogador iniciarRodada(){
        
        // Distribui cartas inicias e reseta o estado dos jogadores.
        distribuirCartas();
        
        /* Loop que continua enquanto nenhum jogador atingir 
        a pontuação necessária para o turno acabar. */
        while(jogador1.getTurnPontos() < TURNOS_PARA_VENCER &&
                jogador2.getTurnPontos() < TURNOS_PARA_VENCER){
                        
            // Cria um turno entre os jogadores atuais.
            Turno turno = new Turno(jogador1, jogador2);
            
            // Executa o turno e captura o vencedor.
            Jogador vencedorTurno = turno.jogar();
            
            // Atualiza pontuação e exibe o resultado do turno.
            if(vencedorTurno == jogador1) {
                ViewRodada.ganhadorTurno(jogador1);
                jogador1.ganhouTurno();
            } else {
                ViewRodada.ganhadorTurno(jogador2);
                jogador2.ganhouTurno();
            }
            
            Input.separador();
            
            /* Lógica do jogo onde que o jogador que vencer o turno
            será o primeiro a jogar no próximo turno. */
            if(vencedorTurno == jogador2) {
                Jogador temp = jogador1;
                jogador1 = jogador2;
                jogador2 = temp;
            }
        }
        
        /* Verifica qual jogador alcançou 2 pontos primeiro, depois retorna
        para que que seja incrementado um ponto na classe da partida. */
        if (jogador1.getTurnPontos() >= TURNOS_PARA_VENCER) {
        return jogador1;
        }
        return jogador2;
    }
    
    /* Distribui as cartas do baralho para os jogadores
    e reinicia a pontuação de turno. */
    private void distribuirCartas() {
        baralho.distribuirCarta(jogador1);
        baralho.distribuirCarta(jogador2);
        jogador1.zerarTurnos();
        jogador2.zerarTurnos();
    }

}