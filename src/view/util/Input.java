package view.util;

import java.util.Scanner;

/**
 * Classe utilitária para leitura e validação de entradas do usuário.
 *  
 * @author gbrsnts
 */
public class Input {
    private static Scanner scanner = new Scanner(System.in);
    
    // Valida se é um número inteiro válido.
    public static int lerInt(String mensagem){
        while(true){
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim();
            
            if(entrada.isEmpty()) {
                System.out.println("Entrada vazia! Digite um numero.");
                continue;
            }
            
            try{
                return Integer.parseInt(entrada);
            } catch(NumberFormatException e){
                System.out.println("Entrada invalida! Digite um numero.");
            }
        }
    }
    
    // Valida se é um CHAR válido entre 'S' ou 'N'.
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
    
    // Valida se é um String válido e não uma entrada vazia.
    public static String lerString(String mensagem){
        while(true){
            System.out.print(mensagem);
            
            String entrada = scanner.nextLine().trim();
            
            if(entrada.isEmpty()){
                System.out.println("Entrada vazia!");
                continue;
            }
            
            return entrada;
        }
    }
    
    public static void linha() {
        System.out.println("=================================");
    }

    public static void separador() {
        System.out.println("---------------------------------");
    }
}
