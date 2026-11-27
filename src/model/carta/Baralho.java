package model.carta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;
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
    
    /* Flatmap reúne todos os possíveis resultados de todos os naipes.
     * Map combina cada valor possível, criando um objeto Carta.
     * ToCollection coloca todas as cartas em um ArrayList */
    private void montarBaralho(){
        cartas = Arrays.stream(Naipe.values())
                .flatMap(naipe -> Arrays.stream(ValorCarta.values())
                .map(valor -> new Carta(valor, naipe)))
                .collect(Collectors.toCollection(ArrayList::new));
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
