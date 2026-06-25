
package model.carta;

import java.util.Objects;

/**
 * Classe representante de cada carta do baralho, composta por um valor e naipe.
 * @author gbrsnts
 */
public class Carta {
    private ValorCarta valor;
    private Naipe naipe;
    /**
     * Cria uma carta com valor e naipe definidos.
     */
    
    public Carta(ValorCarta valor, Naipe naipe){
        if (valor == null || naipe == null)
            throw new IllegalArgumentException("Valor e naipe não podem ser nulos.");
        this.valor = valor;
        this.naipe = naipe;
    }
    
    public int getValor(){
        return valor.getValor();
    }
    
     /**
     * Retorna uma representação da carta, com seu símbolo e naipe
     */
    
    @Override
    public String toString(){
        return valor.getSimbolo() + " de " + naipe;
    }
    
    /* Verifica se duas cartas são iguais.
    * Duas cartas são iguais quando possuem o mesmo valor e o mesmo naipe.*/
    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;

        if(!(obj instanceof Carta))
            return false;

        Carta outra = (Carta) obj;

        return valor == outra.valor
                && naipe == outra.naipe;
    }

    
    
    /* Gera um código hash para a carta com base
    * em seu valor e naipe.*/
    @Override
    public int hashCode() {
        return Objects.hash(valor, naipe);
    }
}
