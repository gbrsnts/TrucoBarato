package view.game;

import model.jogador.Jogador;

/**
 * Classe responsável pelos outputs da classe "rodada".
 * 
 * @author gbrsnts
 */
public class ViewRodada {
    
    // Exibe informação de vitória do turno.
    public static void ganhadorTurno(Jogador jogador){
        System.out.println("\u001B[31m" 
        + jogador.getNome() 
        + " ganhou o turno.\u001B[0m");
    }
    
}
