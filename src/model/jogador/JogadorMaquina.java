package model.jogador;

import java.util.Random;
import model.jogador.Jogador;
import model.carta.Carta;

/**
 * Classe que representa o jogador controlado pela máquina.
 * 
 * A classe herda de jogador e implementa o comportamento de escolher carta.
 * 
 * @author gbrsnts
 */
public class JogadorMaquina extends Jogador {
    private Random random = new Random();
    
    public JogadorMaquina(String nome) {
        super(nome);
    }

    @Override
    public Carta escolherCarta(int indice) {
        // Gera um índice aleatório baseado no tamanho da mão.
        int index = random.nextInt(mao.size());
        
        // Remove e retorna a carta escolhida.
        return mao.remove(index);
    }
    
}
