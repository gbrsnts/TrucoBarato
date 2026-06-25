package view.util;

import java.util.Scanner;

/**
 *
 * @author gbrsnts
 */
public class Input {
    private static Scanner scanner = new Scanner(System.in);
    
    public static int lerInt(String mensagem){
        while(true){
            System.out.println(mensagem);
            String entrada = scanner.nextLine().trim();
            
            if(entrada.isEmpty()) {
                System.out.print("Entrada vazia! Digite um numero.");
                continue;
            }
            
            try{
                return Integer.parseInt(entrada);
            } catch(NumberFormatException e){
                System.out.println("Entrada invalida! Digite um numero.");
            }
        }
    }
    
    public static char lerChar(String mensagem){
        while(true){
            System.out.println(mensagem);
            
            String entrada = scanner.nextLine().trim();
            
            if(entrada.isEmpty()){
                System.out.println("Entrada vazia! Digite 'S' ou 'N'.");
                continue;
            }
            
            char c = Character.toLowerCase(entrada.charAt(0));
            
            if (c == 's' || c == 'n'){
                return c;
            }
            
            System.out.println("Entrada inválida! Digite apenas 'S' ou 'N'.");
        }
    }           
    
}
