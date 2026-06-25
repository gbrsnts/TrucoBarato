package model.jogador;

import java.util.InputMismatchException;
import model.DAO.RegistroJogador;
import model.carta.Carta;
import model.jogador.Jogador;

/**
 * Classe para o jogador humano que herda atributos e métodos de jogador
 *
 * @author gbrsnts
 */
public class JogadorHumano extends Jogador {    
    private RegistroJogador registro;
    
    public JogadorHumano(RegistroJogador registro) {
        super(registro.getName());
        this.registro = registro;
    }
    
    public RegistroJogador getRegistro(){
        return registro;
    }
    
    /**
     * A implementação deste modo difere do jogadorMaquina, 
     * pois o jogadorHumano precisa receber um indice para remover a carta
     * da mão do jogador.
     */
    
    @Override
    public Carta escolherCarta(int indice) {    
        return mao.remove(indice);
    }
}
