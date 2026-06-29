package model.jogador;

import java.util.ArrayList;
import java.util.List;
import model.carta.Carta;

/**
 * Classe abstrata que é utilizado como base de cada jogador.
 * Concentra toda a lógica comum entre jogadorHumano e jogadorMaquina.
 * 
 * @author gbrsnts
 */
public abstract class Jogador {
    /**
     * Array list para ser a "mão" do jogador, com suas respectivas cartas.
     * Variável de pontosPartida para atribuir um placar ao jogo
     */
    private List<Carta> mao = new ArrayList<>();
    private int roundPoints;
    private int turnPoints;
    private String nome;
    
    public Jogador(String nome) {
        setNome(nome);
    }
    
    // Adiciona uma carta à mão do jogador.
    public void receberCarta(Carta carta){
        mao.add(carta);
    }
    
    // Retorna todas as cartas que o jogador possui na mão.
    public List<Carta> getMao(){
        return mao;
    }
    
    // Remove a carta no index passado.
    public Carta removerCarta(int index){
        return mao.remove(index);
    }
    
    // Retorna quantas cartas há na mão do jogador.
    public int tamanhoMao(){
        return mao.size();
    }
    
    // Remove todas as cartas da mão do jogador.
    public void limparMao(){
        mao.clear();
    }
    
    // Retorna pontuação de rodadas dentro da partida.
    public int getRoundPoints(){
        // Informar quantos pontos tem o jogador
        return this.roundPoints;
    }
    
    // Incrementa a pontuação de rodada dentro da partida.
    public void ganhouRodada(){
        this.roundPoints++;
    }
    
    // Retorna pontuação de turno dentro da rodada.
    public int getTurnPoints() {
        return this.turnPoints;
    }
    
    // Incrementa a pontuação de turno dentro da rodada.
    public void ganhouTurno(){
        this.turnPoints++;
    }
    
    // Zera os pontos de turno no início de cada rodada.
    public void zerarTurnos(){
        this.turnPoints = 0;
    }

    // Define o nome do jogador com validação.
    public void setNome(String nome) {
        
        // Verifica se o nome do jogador não está nulo ou em branco
        if(nome == null || nome.isBlank()){
            throw new IllegalArgumentException(
                "Nome inválido"
            );
        }
        
        this.nome = nome;
    }

    // Retorna o nome do jogador.
    public String getNome() {
        return nome;
    }
    
    /**
     * Método abstrato que define o comportamento de escolher carta.
     * Cada tipo de jogador deve implementar sua própria lógica.
     */
    public abstract Carta escolherCarta(int indice);
    
}
