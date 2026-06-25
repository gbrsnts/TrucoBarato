package model.jogador;

import java.util.InputMismatchException;
import model.carta.Carta;
import model.jogador.Jogador;
import java.util.Scanner;

/**
 * Classe para o jogador humano que herda atributos e métodos de jogador
 *
 * @author gbrsnts
 */
public class JogadorHumano extends Jogador {    
    private Scanner scanner = new Scanner(System.in);
    private int id;
    
    public JogadorHumano(int id, String nome) {
        super(nome);
        this.id = id;
    }
    
    /**
     * A implementação deste modo difere do jogadorMaquina, pois abre opção
     * para que o jogador escolha a carta que ache agradável, após escolher
     * remove a carta da mão do jogador.
     */
    
    public int getId() {
       return id;
    }
    
    @Override
    public Carta escolherCarta() {    
        while(true){
            for(int i = 0; i < mao.size(); i++){
                System.out.println(i+1 + " - " + mao.get(i));
            }
            
            System.out.print("Escolha uma carta: ");
            
            try{
                int escolha = scanner.nextInt();
                
                if (escolha >= 1 && escolha <= mao.size()){
                    return mao.remove(escolha-1);
                }   
            
                System.out.println("Opcao invalida!");
            } catch (Exception e){
                System.out.println("Digite apenas numeros!");
                scanner.nextLine();
            }
            
        }        
    }
    
}
