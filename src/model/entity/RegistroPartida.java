package model.entity;

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
    private RegistroJogador jogador;
    private Integer pontosJogador;
    private Integer pontosMaquina;
    private Boolean venceu;    
    private LocalDateTime playedAt;
    
    protected RegistroPartida(){
    }    
    
    public RegistroPartida(RegistroJogador perfil, Integer pontosJogador,
                           Integer pontosMaquina, Boolean venceu){
        this.jogador = perfil;
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
    
    // Retorna o jogador da partida
    public RegistroJogador getJogador(){
        return jogador;
    }
    
    // Atalho para o ID do jogador da partida
    public Integer getJogadorId(){
        return jogador.getId();
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
    
    // Caso as partidas tenham o mesmo ID são consideradas iguais.
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof RegistroPartida outra)) return false;
        return id != null && id.equals(outra.id);
    }
    
    // Valor de hashCode é fixo por classe
    @Override
    public int hashCode(){
        return getClass().hashCode();
    }
    
    /* Retorna o ID, placar e data/hora da partida.
     Utilizando quando for listar o histórico de partida. */
    @Override
    public String toString(){
        return "[" + id + "] JogadorID=" + jogador.getId() +
            " Placar=" + pontosJogador + " x " + pontosMaquina +
            " Data=" + playedAt;
    }
}
