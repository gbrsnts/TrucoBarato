package controller;

import model.DAO.HistoricoDAO;
import model.DAO.RegistroJogador;
import model.jogador.Jogador;
import model.jogador.JogadorHumano;
import model.jogador.JogadorMaquina;
import model.game.Partida;
import view.game.ViewPartida;

/**
 * Classe responsável pelo controle e gerenciamento das partidas.
 * A classe atua como intermediária entre a interface do usuário (View)
 * e lógica do jogo (Model)
 * 
 * @author gbrsnts
 */
public class JogoController {
    // RELACIONAMENTO: AGREGAÇÃO
    // O controller apenas utiliza o DAO, sem controler seu ciclo de vida.
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
        
        Jogador vencedor;
        try {
            vencedor = partida.iniciarPartida();
        } catch (Partida.DesistenciaException e){
            ViewPartida.desistencia(humano);
            return;
        }
        
        Integer pontosJogador = humano.getRoundPoints();
        Integer pontosMaquina = maquina.getRoundPoints();
        Boolean venceu = (vencedor == humano);
        
        /* Armazena o resultado da partida no histórico.
        Associando o perfil de um jogador em cada partida. */
        historicoDAO.adicionar(perfil, pontosJogador, pontosMaquina, venceu);
    }
    
}