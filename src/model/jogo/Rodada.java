package model.jogo;

import model.carta.Baralho;
import model.jogador.Jogador;
import model.jogo.Turno;
import view.Tela;
import view.game.ViewRodada;

/**
 * Classe responsável pela classe e lógica de cada rodada
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
        distribuirCartas();
        
        while(jogador1.getTurnPontos() < TURNOS_PARA_VENCER &&
                jogador2.getTurnPontos() < TURNOS_PARA_VENCER){
                        
            Turno turno = new Turno(jogador1, jogador2);
            
            Jogador vencedorTurno = turno.jogar();
            
            if(vencedorTurno == jogador1) {
                ViewRodada.ganhadorTurno(jogador1);
                jogador1.ganhouTurno();
            } else {
                ViewRodada.ganhadorTurno(jogador2);
                jogador2.ganhouTurno();
            }
            Tela.separador();
            if(vencedorTurno == jogador2) {
                Jogador temp = jogador1;
                jogador1 = jogador2;
                jogador2 = temp;
            }
        }
        
        /*
        Verifica qual jogador alcançou 2 pontos primeiro, depois retorna
        para que que seja incrementado um ponto na classe da partida.
        */
        
        if (jogador1.getTurnPontos() >= TURNOS_PARA_VENCER) {
        return jogador1;
        }
        return jogador2;
    }
    
    private void distribuirCartas() {
        baralho.distribuirCarta(jogador1);
        baralho.distribuirCarta(jogador2);
        jogador1.zerarTurnos();
        jogador2.zerarTurnos();
    }

}