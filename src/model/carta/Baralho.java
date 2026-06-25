package model.carta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.jogador.Jogador;

/**
 * Classe responsável pelo baralho
 * @author gbrsnts
 */
public class Baralho {
    private List<Carta> cartas = new ArrayList<>();
    
    /**
     * O construtor de baralho já vai montar o baralho e embalhar cada partida.
     */
    public Baralho(){
        montarBaralho();
        embaralhar();
    }
    
    
    /**
     * Gera uma carta com cada valor e naipe e adiciona no baralho.
     */
    private void montarBaralho(){
        for(Naipe naipe : Naipe.values()){
            for(ValorCarta valor : ValorCarta.values()){
                cartas.add(new Carta(valor, naipe));
            }
        }
    }
    
    /**
     * Embaralha utilizando o método shuffle da colletion.
     */    
    public void embaralhar(){
        Collections.shuffle(cartas);
    }
    
    
    /**
     * Remove a primeira carta do baralho e retorna qual foi removida.
     */
    public Carta comprar(){
        return cartas.remove(0);
    }
    
    /**
     * Método para distribuir 3 cartas para cada jogador.
     * Primeiramente limpa a mão do jogador
     * Logo dá três cartas para o jogador, removedo-as do baralho.
     */
    public void distribuirCarta(Jogador jogador){
        jogador.limparMao();
        for(int i = 0; i < 3; i++){
            jogador.receberCarta(comprar());
        }
    }
}
