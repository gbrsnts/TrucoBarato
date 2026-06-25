package view.game;

import model.jogador.Jogador;

/**
 *
 * @author gbrsnts
 */
public class ViewRodada {
    public static void ganhadorTurno(Jogador jogador){
        System.out.println("\u001B[31m" 
        + jogador.getNome() 
        + " ganhou o turno.\u001B[0m");
    }
    
}
