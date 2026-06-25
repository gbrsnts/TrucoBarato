package model.jogo;

import model.carta.Carta;
import model.jogador.Jogador;
import model.jogador.JogadorHumano;
import view.game.ViewTurno;

/**
 * Classe responsável pela classe e lógica de cada turno
 * 
 * @author gbrsnts
 */
public class Turno {
    private Jogador primeiro;
    private Jogador segundo;
    
    public Turno(Jogador primeiro, Jogador segundo){
        this.primeiro = primeiro;
        this.segundo = segundo;
    }
    
    public Jogador jogar(){
        Carta carta1 = jogarCarta(primeiro);    
        ViewTurno.mostrarJogada(primeiro, carta1);        
        
        Carta carta2 = jogarCarta(segundo);
        ViewTurno.mostrarJogada(segundo, carta2);
        
        if (carta1.getValor() > carta2.getValor()) {
            return primeiro;
        }

        if (carta2.getValor() > carta1.getValor()) {
            return segundo;
        }

        return primeiro;
    }
    
    private Carta jogarCarta(Jogador jogador) {

        if (jogador instanceof JogadorHumano) {
            int indice = ViewTurno.escolherCarta(jogador);
            return jogador.escolherCarta(indice);
        }

        return jogador.escolherCarta(0);
    }
}
