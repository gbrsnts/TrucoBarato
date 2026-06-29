package model.carta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.jogador.Jogador;

/**
 * Classe responsável por gerenciar o baralho do jogo.
 * 
 * @author gbrsnts
 */
public class Baralho {
    private List<Carta> cartas = new ArrayList<>();
   
    public Baralho(){
        
        /* Quando um baralho é criado: todas as cartas são geradas
        e o baralho é embaralhado automaticamente. */
        montarBaralho();
        embaralhar();
    }
    
    /* Gera todas as cartas possível do baralho.
     * Percorrendo todos os naipes e valores, 
     * realizando uma combinação pra cada carta. */
    private void montarBaralho(){
        for(Naipe naipe : Naipe.values()){
            for(ValorCarta valor : ValorCarta.values()){
                cartas.add(new Carta(valor, naipe));
            }
        }
    }
    
    // Embaralha utilizando o método shuffle de collection.
    public void embaralhar(){
        Collections.shuffle(cartas);
    }
    
    // Retira uma carta do "topo" do baralho, retornando a mesma.
    public Carta comprar(){
        return cartas.remove(0);
    }
    
    // Distribui 3 cartas para um jogador, removedo-as do baralho.
    public void distribuirCarta(Jogador jogador){
        jogador.limparMao();
        for(int i = 0; i < 3; i++){
            jogador.receberCarta(comprar());
        }
    }
}
