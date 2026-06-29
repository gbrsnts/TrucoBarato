package model.game;

import model.carta.Carta;
import model.jogador.Jogador;
import model.jogador.JogadorHumano;
import view.game.ViewTurno;

/**
 * Classe responsável pela lógica de cada turno.
 * 
 * @author gbrsnts
 */
public class Turno {
    // RELACIONAMENTO: AGREGAÇÃO
    // Os jogadores são apenas referenciados no turno, não pertencem a ele.
    private Jogador primeiro;
    private Jogador segundo;
    
    public Turno(Jogador primeiro, Jogador segundo){
        this.primeiro = primeiro;
        this.segundo = segundo;
    }
    
    public Jogador jogar(){
        Carta carta1 = jogarCarta(primeiro);    
        
        // Exibe na view a carta escolhida.
        ViewTurno.mostrarJogada(primeiro, carta1);        
        
        Carta carta2 = jogarCarta(segundo);
        
        // Exibe na view a carta escolhida.
        ViewTurno.mostrarJogada(segundo, carta2);
        
        // Realiza a comparação entre as cartas jogadas
        if (carta1.getValor() > carta2.getValor()) return primeiro;
        if (carta2.getValor() > carta1.getValor()) return segundo;

        // Regra de desempate: caso ambas cartas tenha o mesmo valor,
        // O primeiro a jogar a carta será o vencedor.
        return primeiro;
    }
    
    /* Executa a jogada de cada jogador.
    Esse método diferencia o jogador de humano e máquina. */
    private Carta jogarCarta(Jogador jogador) {
        
        // Verifica se o jogador é humano para o solicitar input ou não.
        if (jogador instanceof JogadorHumano) {
            
            // A view solicita que o jogador escolha uma carta.
            int indice = ViewTurno.escolherCarta(jogador);
            
            // Retorna a carta escolhida pelo jogador humano.
            return jogador.escolherCarta(indice);
        }
        
        // Caso seja a vez da máquina, será sempre passado o índice 0.
        return jogador.escolherCarta(0);
    }
}
