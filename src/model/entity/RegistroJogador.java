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
    
    // Identificador da versão da classe para compatibilidade na serialização.
    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private LocalDateTime createdAt;
    private String name;
    
    protected RegistroJogador(){
    }
    
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
    
    // Caso os perfis tenham o mesmo ID são considerados iguais.
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof RegistroJogador outro)) return false;
        return id != null && id.equals(outro.id);
    }
       
    // Valor de hashCode é fixo por classe
    @Override
    public int hashCode(){    
        return getClass().hashCode();
    }
    
    /* Retorna o ID e nome de cada jogador.
     Utilizando quando for exibido em listas ou menus. */
    @Override
    public String toString() {
        return "[" + id + "] " + name;
    }
}
