package view.util;

import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Classe utilitária para leitura e validação de entradas do usuário.
 *  
 * @author gbrsnts
 */
public class Input {
    private static Scanner scanner = new Scanner(System.in);
    
    /* Método genérico de leitura.
    * Lê uma linha, converte para o tipo T com o conversor.
    * Confere com o valido e repete até a entrada ser aceita.
    */
    public static <T> T ler(String mensagem,
            Function<String, T> conversor,
            Predicate<T> valido,
            String mensagemErro){
        while(true){
            System.out.println(mensagem);
            String entrada = scanner.nextLine().trim();
            
            if(entrada.isEmpty()){
                System.out.println("Entrada vazia!");
                continue;
            }
            
            try{
                T valor = conversor.apply(entrada);
                
                if(valido.test(valor)){
                    return valor;
                }
            } catch(IllegalArgumentException e){
                
            }
            System.out.println(mensagemErro);
        }
    }
    
    // Lê um número inteiro.
    public static int lerInt(String mensagem){
        return ler(mensagem, Integer::parseInt, n-> true, "Entrada invalida! Digite um numero.");
    }
    
    // Lê 'S' ou 'N' e devolve sempre em minúsculo.
    public static char lerChar(String mensagem){
        return ler(mensagem, s-> Character.toLowerCase(s.charAt(0)), c-> c == 's' || c == 'n',
                "Entrada invalida! Digite apenas 'S' ou 'N'.");
    }
    
    // Lê um texto não vazio
    public static String lerString(String mensagem){
        return ler(mensagem, s-> s, s-> true, "Entrada invalida!");
    }
    
    public static void linha() {
        System.out.println("=================================");
    }

    public static void separador() {
        System.out.println("---------------------------------");
    }
}
