package controller;

import model.DAO.HistoricoDAO;
import model.DAO.RegistroJogador;
import model.jogador.JogadorHumano;
import model.jogador.JogadorMaquina;
import model.jogo.Partida;
import view.Tela;

/**
 *
 * @author gbrsnts
 */
public class JogoController {
    private HistoricoDAO historicoDAO;
    
    public JogoController(HistoricoDAO historicoDAO){
        this.historicoDAO = historicoDAO;
    }

    public void iniciarPartida(RegistroJogador perfil){
        // Instanciar o jogador humano e máquina para serem utilizados na partida.
        JogadorHumano humano = new JogadorHumano(perfil.getId(), perfil.getName());
        JogadorMaquina maquina = new JogadorMaquina("Stuart");
        
        // Instanciar a partida que irá iniciar.
        Partida partida = new Partida(humano, maquina);
        
        String placar = partida.iniciarPartida();
        
        historicoDAO.adicionar(humano.getId(), placar);
    }
    
}