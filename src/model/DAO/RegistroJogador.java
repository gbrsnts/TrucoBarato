package model.DAO;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Classe responsável por respresentar um perfil de jogador.
 * Cada objeto dessa classe armazena informações básicas do jogador.
 * 
 * @author gbrsnts
 */
public class RegistroJogador implements Serializable{
    
    /* Atributo responsável  por controlar o próximo ID disponível.
    Por ser compartilhado entre todos os objetos da classe é static, garantindo
    que cada jogador receba um valor único.
    */
    private static int proximoId = 1;
    
    private int id;
    private LocalDateTime createdAt;
    private String name;
    
    public RegistroJogador(String name){
        this.id = proximoId++;
        this.createdAt = LocalDateTime.now();
        this.name = name;
    }
    
    // Atualiza o contador global de ID.
    public static void atualizarId(int novoId){
        if(novoId > proximoId){
            proximoId = novoId;
        }
    }
    
    // Retornar o ID do jogador.
    public int getId(){
        return id;
    }
    
    // Retorna a data de criação do perfil.
    public LocalDateTime getCreatedAt(){
        return createdAt;
    }
    
    // Retorna o nome do jogador.
    public String getName(){
        return name;
    }

    /* Retorna o ID e nome de cada jogador.
     Utilizando quando for exibido em listas ou menus. 
     */
    @Override
    public String toString() {
        return "[" + id + "] " + name;
    }
}
