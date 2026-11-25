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
    private static final long serialVersionUID = 1L;
    private static int proximoId = 1;
    
    private Integer id;
    private Integer jogadorId;
    private Integer pontosJogador;
    private Integer pontosMaquina;
    private Boolean venceu;    
    private LocalDateTime playedAt;
    
    
    public RegistroPartida(RegistroJogador perfil, Integer pontosJogador,
                           Integer pontosMaquina, Boolean venceu){
        this.id = proximoId++;
        this.jogadorId = perfil.getId();
        this.pontosJogador = pontosJogador;
        this.pontosMaquina = pontosMaquina;
        this.venceu = venceu;
        this.playedAt = LocalDateTime.now();
    }
    
    // Atualiza o contador global de ID.
    public static void atualizarId(int novoId){
        if(novoId > proximoId){
            proximoId = novoId;
        }
    }
    
    // Retornar o ID da partida
    public Integer getId(){
        return id;
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
    
    public void setId(Integer id) { 
        this.id = id; 
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
