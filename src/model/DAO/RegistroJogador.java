package model.DAO;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author gbrsnts
 */
public class RegistroJogador implements Serializable{
    private static int proximoId = 1;
    
    private int id;
    private LocalDateTime createdAt;
    private String name;
    
    public RegistroJogador(String name){
        this.id = proximoId++;
        this.createdAt = LocalDateTime.now();
        this.name = name;
    }
    
    public static void atualizarId(int novoId){
        if(novoId > proximoId){
            proximoId = novoId;
        }
    }
    
    public int getId(){
        return id;
    }
    
    public LocalDateTime getCreatedAt(){
        return createdAt;
    }
    
    public String getName(){
        return name;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + name;
    }
    
    
}
