package model.DAO;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Classe responsável por representar o registro de uma partida.
 * Cada objeto dessa classe armazena informações referentes a partida.
 * 
 * @author gbrsnts
 */
public class RegistroPartida implements Identificavel<Integer>, Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private Integer jogadorId;
    private Integer pontosJogador;
    private Integer pontosMaquina;
    private Boolean venceu;
    private LocalDateTime playedAt;
    
    
    public RegistroPartida(RegistroJogador perfil, Integer pontosJogador,
                           Integer pontosMaquina, Boolean venceu){
        this.jogadorId = perfil.getId();
        this.pontosJogador = pontosJogador;
        this.pontosMaquina = pontosMaquina;
        this.venceu = venceu;
        this.playedAt = LocalDateTime.now();
    }
    
    // Retornar o ID da partida
    @Override
    public Integer getId(){
        return id;
    }
    
    // Define o ID da partida
    @Override
    public void setId(Integer id){
        this.id = id;
    }
    
    // Retorna o ID do jogador da partida.
    public Integer getJogadorId(){
        return jogadorId;
    }
    
    
    // Retorna os pontos do jogador.
    public Integer getPontosJogador(){
        return pontosJogador;
    }

    // Retorna os pontos da máquina.
    public Integer getPontosMaquina(){
        return pontosMaquina;
    }

    // Retorna status da partida.
    public Boolean getVenceu(){
        return venceu;
    }
    
    // Retorna data e hora em que a partida foi realizada.
    public LocalDateTime getPlayedAt(){
        return playedAt;
    }
    
    /* Retorna o ID, placar e data/hora da partida.
     Utilizando quando for listar o histórico de partida. */
    @Override
    public String toString(){
        return "[" + id + "] JogadorID=" + jogadorId +
            " Placar=" + pontosJogador + " x " + pontosMaquina +
            " Data=" + playedAt;
    }
}
