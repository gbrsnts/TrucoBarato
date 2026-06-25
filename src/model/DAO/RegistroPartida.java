package model.DAO;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author gbrsnts
 */
public class RegistroPartida implements Serializable{
    private static int proximoId = 1;
    
    private int id;
    private int jogadorId;
    private String placar;
    private LocalDateTime playedAt;
    
    
    public RegistroPartida(int jogadorId, String placar){
        this.id = proximoId++;
        this.jogadorId = jogadorId;
        this.placar = placar;
        this.playedAt = LocalDateTime.now();
    }
    
    public static void atualizarId(int novoId){
        if(novoId > proximoId){
            proximoId = novoId;
        }
    }
    
    public int getId(){
        return id;
    }
    
    public int getJogadorId(){
        return jogadorId;
    }
    
    public String getPlacar(){
        return placar;
    }
    
    public LocalDateTime getPlayedAt(){
        return playedAt;
    }
    
    @Override
    public String toString(){
        return "[" + id + "] JogadorID=" + jogadorId +
            " Placar=" + placar +
            " Data=" + playedAt;
    }
}
