package controller;

import model.DAO.HistoricoDAO;
import model.DAO.RegistroJogador;
import model.jogador.JogadorHumano;
import model.jogador.JogadorMaquina;
import model.game.Partida;

/**
 * Classe responsável pelo controle e gerenciamento das partidas.
 * A classe atua como intermediária entre a interface do usuário (View)
 * e lógica do jogo (Model)
 * 
 * @author gbrsnts
 */
public class JogoController {
    
    // Declara objeto responsável pela persistência do histórico.
    private HistoricoDAO historicoDAO;
    
    // Recebe uma instância do DAO de histórico, permitindo registrar o resultado.
    public JogoController(HistoricoDAO historicoDAO){
        this.historicoDAO = historicoDAO;
    }

    // Inicia uma nova partida.
    public void iniciarPartida(RegistroJogador perfil){
        // Cria o jogador humano da partida.
        JogadorHumano humano = new JogadorHumano(perfil);
        
        // Cria o oponente do jogador humano.
        JogadorMaquina maquina = new JogadorMaquina("Stuart");
        
        // Cria uma nova partida contendo os dois jogadores.
        Partida partida = new Partida(humano, maquina);
        
        String placar = partida.iniciarPartida();
        
        /* Armazena o resultado da partida no histórico.
        Associando o perfil de um jogador em cada partida. */
        historicoDAO.adicionar(perfil, placar);
    }
    
}