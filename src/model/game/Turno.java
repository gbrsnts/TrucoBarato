package model.game;

import model.carta.Carta;
import model.jogador.Jogador;
import model.jogador.JogadorHumano;
import view.game.ViewTurno;
import java.util.Comparator;

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
    
    // Comparator a partir de uma referência de método
    private static final Comparator<Carta> POR_VALOR = Comparator.comparingInt(Carta::getValor);
    
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
        int comparacao = POR_VALOR.compare(carta1, carta2);
        
        // Regra de desempate: caso ambas cartas tenha o mesmo valor,
        // O primeiro a jogar a carta será o vencedor.
        return (comparacao >= 0) ? primeiro : segundo;
    }
    
    /* Executa a jogada de cada jogador.
    Esse método diferencia o jogador de humano e máquina. */
    private Carta jogarCarta(Jogador jogador) {
        
        // Verifica se o jogador é humano para o solicitar input ou não.
        if (jogador instanceof JogadorHumano) {
            
            // A view solicita que o jogador escolha uma carta.
            int indice = ViewTurno.escolherCarta(jogador);
            
            // Interrompe a partida inteira
            if (indice == ViewTurno.DESISTIR){
                throw new Partida.DesistenciaException();
            }
            
            // Retorna a carta escolhida pelo jogador humano.
            return jogador.escolherCarta(indice);
        }
        
        // Caso seja a vez da máquina, será sempre passado o índice 0.
        return jogador.escolherCarta(0);
    }
}
