package model.carta;

/**
 * Enum responsável por representar os possíveis
 * valores de carta e seus respectivos símbolos.
 * 
 * @author gbrsnts
 */
public enum ValorCarta {
    DOIS(2, "2"),
    TRES(3, "3"),
    QUATRO(4, "4"),
    CINCO(5, "5"),
    SEIS(6, "6"),
    SETE(7, "7"),
    OITO(8, "8"),
    NOVE(9, "9"),
    DEZ(10, "10"),
    VALETE(11, "J"),
    DAMA(12, "Q"),
    REI(13, "K"),
    AS(14, "A");
    
    private int valor;
    private String simbolo;
    
    ValorCarta(int valor, String simbolo){
        this.valor = valor;
        this.simbolo = simbolo;
    }
    
    /* Retorna o valor número da carta.
    Utilizado para determinar qual carta irá vencer em comparação. */
    public int getValor(){
        return valor;
    }
    
    /* Retorna o símbolo da carta.
    Utilizado para exibição para o usuário. */
    public String getSimbolo(){
        return simbolo;
    }
    
}
