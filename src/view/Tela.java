package view;

import model.jogador.Jogador;
/**
 *
 * @author gbrsnts
 */
public class Tela {
    public static void linha() {
        System.out.println("=================================");
    }

    public static void separador() {
        System.out.println("---------------------------------");
    }
    
    public static void cabecalho(int numeroRodada, 
            Jogador jogadorHumano, Jogador jogadorMaquina){
        Tela.linha();
        System.out.println("RODADA " + numeroRodada);
        Tela.separador();
        
        System.out.printf(
            "%s %d x %d %s%n%n",
            jogadorHumano.getNome(),
            jogadorHumano.getRoundPoints(),
            jogadorMaquina.getRoundPoints(),
            jogadorMaquina.getNome()
        );
    }
    
    public static void ganhadorRodada(Jogador jogador){
    System.out.println("\u001B[34m" 
        + jogador.getNome() 
        + " ganhou a rodada.\u001B[0m");
}

    
}