package model.jogador;

import java.util.Random;
import model.jogador.Jogador;
import model.carta.Carta;

/**
 * Classe onde define alguns comportamentos do jogadorMaquina
 * @author gbrsnts
 */
public class JogadorMaquina extends Jogador {
    private Random random = new Random();
    
    public JogadorMaquina(String nome) {
        super(nome);
    }

    @Override
    public Carta escolherCarta(int indice) {
        int index = random.nextInt(mao.size());
        return mao.remove(index);
    }
    
}
