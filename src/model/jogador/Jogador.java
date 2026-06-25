package model.jogador;

import java.util.ArrayList;
import java.util.List;
import model.carta.Carta;

/**
 * Classe abstrata que é utilizado como base de cada jogador.
 * @author gbrsnts
 */
public abstract class Jogador {
    /**
     * Array list para ser a "mão" do jogador, com suas respectivas cartas.
     * Variável de pontosPartida para atribuir um placar ao jogo
     */
    protected List<Carta> mao = new ArrayList<>();
    private int roundPoints;
    private int turnPoints;
    private String nome;
    
    public Jogador(String nome) {
        setNome(nome);
    }
    
    public void receberCarta(Carta carta){
        // Adicionar uma carta na mão do jogador
        mao.add(carta);
    }
    
    public List<Carta> getMao(){
        // Mostrar as cartas da mão do jogador
        return mao;
    }
    
    public void limparMao(){
        // Limpar a mão após cada rodada
        mao.clear();
    }
    
    public int getRoundPoints(){
        // Informar quantos pontos tem o jogador
        return this.roundPoints;
    }
    
    public void ganhouRodada(){
        // Adicionar um ponto ao jogador ao ganhar a rodada
        this.roundPoints++;
    }
    
    public int getTurnPontos(){
        // Informar quantos pontos tem o jogador
        return this.turnPoints;
    }
    
    public void ganhouTurno(){
        // Adicionar um ponto ao jogador ao ganhar o turno
        this.turnPoints++;
    }
    
    public void zerarTurnos(){
        // Zera os pontos de turno no início de cada rodada
        this.turnPoints = 0;
    }

    public void setNome(String nome) {
        // Verifica se o nome do jogador não está nulo ou em branco
        if(nome == null || nome.isBlank()){
            throw new IllegalArgumentException(
                "Nome inválido"
            );
        }
        // Configura um nome para o jogador
        this.nome = nome;
    }

    public String getNome() {
        // Retorna o nome do jogador
        return nome;
    }
    
    /**
     * Método abstrato para adequar a escolha da carta para cada classe
     */
    public abstract Carta escolherCarta(int indice);
    
}
