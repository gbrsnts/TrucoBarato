package model.DAO;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Classe responsável por representar o registro de uma partida.
 * Cada objeto dessa classe armazena informações referentes a partida.
 * 
 * @author gbrsnts
 */
public class RegistroPartida implements Serializable{
    
    /* Atributo responsável  por controlar o próximo ID disponível.
    Por ser compartilhado entre todos os objetos da classe é static, garantindo
    que cada partida receba um valor único.
    */
    private static int proximoId = 1;
    
    private int id;
    private int jogadorId;
    private String placar;
    private LocalDateTime playedAt;
    
    
    public RegistroPartida(RegistroJogador perfil, String placar){
        this.id = proximoId++;
        this.jogadorId = perfil.getId();
        this.placar = placar;
        this.playedAt = LocalDateTime.now();
    }
    
    // Atualiza o contador global de ID.
    public static void atualizarId(int novoId){
        if(novoId > proximoId){
            proximoId = novoId;
        }
    }
    
    // Retornar o ID da partida
    public int getId(){
        return id;
    }
    
    // Retorna o ID do jogador da partida.
    public int getJogadorId(){
        return jogadorId;
    }
    
    
    // Retorna o placar da partida.
    public String getPlacar(){
        return placar;
    }
    
    
    // Retorna data e hora em que a partida foi realizada.
    public LocalDateTime getPlayedAt(){
        return playedAt;
    }
    
    /* Retorna o ID, placar e data/hora da partida.
     Utilizando quando for listar o histórico de partida.
     */
    @Override
    public String toString(){
        return "[" + id + "] JogadorID=" + jogadorId +
            " Placar=" + placar +
            " Data=" + playedAt;
    }
}
