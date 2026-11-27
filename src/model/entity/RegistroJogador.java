package model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Classe responsável por respresentar um perfil de jogador.
 * Cada objeto dessa classe armazena informações básicas do jogador.
 * 
 * @author gbrsnts
 */
public class RegistroJogador implements Identificavel<Integer>, Serializable {
    
    /* Por ser compartilhado entre todos os objetos da classe é static, garantindo
    que cada jogador receba um valor único.
    */
    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private LocalDateTime createdAt;
    private String name;
    
    public RegistroJogador(String name){
        this.createdAt = LocalDateTime.now();
        this.name = name;
    }

    // Retorna o nome do jogador.
    public String getName(){
        return name;
    }    
    
    // Retornar o ID do jogador.
    @Override
    public Integer getId(){
        return id;
    }
    
    // Retorna a data de criação do perfil.
    public LocalDateTime getCreatedAt(){
        return createdAt;
    }
    
    // Define o nome do jogador no perfil.
    public void setName(String name){
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("Nome inválido");
        }
        this.name = name;
    }
    
    // Define o ID do jogador (chamado pelo repositório ao salvar).
    @Override
    public void setId(Integer id){
        this.id = id;
    }
    
    /* Retorna o ID e nome de cada jogador.
     Utilizando quando for exibido em listas ou menus. */
    @Override
    public String toString() {
        return "[" + id + "] " + name;
    }
}
