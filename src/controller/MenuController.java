package controller;

import model.DAO.HistoricoDAO;
import model.DAO.JogadorDAO;
import model.DAO.RegistroJogador;
import model.DAO.RegistroPartida;
import java.util.Collection;

/**
 * Classe responsável pelo controle do menu do jogo.
 * A classe atua como intermediária entre a visualização (View)
 * e as classes de lógica e persistência de dados.
 * 
 * @author gbrsnts
 */
public class MenuController {
    // RELACIONAMENTO: AGREGAÇÃO
    // O controller apenas utiliza os DAOs, sem controler seu ciclo de vida.
    private HistoricoDAO historicoDAO;
    private JogadorDAO jogadorDAO;
    private JogoController jogoController;
    
   public MenuController() {
       historicoDAO = new HistoricoDAO();
       jogadorDAO = new JogadorDAO();
       jogoController = new JogoController(historicoDAO);
   }
   
   // Inicia uma nova partida utilizando o perfil selecionado. 
    public void iniciarPartida() {
        RegistroJogador perfil = jogadorDAO.getPerfilSelecionado();
        jogoController.iniciarPartida(perfil);
    }
    
    // ===================================================
    // Operações relacionados ao histórico.
    // ===================================================
    
    // Retorna todo o histórica das partidas armazendas.
    public Collection<RegistroPartida> listarHistorico(){
        return historicoDAO.listarHistorico();
    }
    
    // ===================================================
    // Operações relacionados ao Jogador.
    // ===================================================
    
    // Cria um novo perfil e seleciona.
    public void criarPerfil(String nome) {
        RegistroJogador registro = jogadorDAO.criarRegistro(nome);
        jogadorDAO.selecionarPerfil(registro.getId());
    }
    
    // Retorna todos perfis de jogadores cadastrados.
    public Collection<RegistroJogador> listarPerfis(){
        return jogadorDAO.listarPerfis();
    }
    
    
    // Define o perfil que será utilizado nas próximas operações.
    public boolean selecionarPerfil(int id){
        return jogadorDAO.selecionarPerfil(id);
    }
    
    // Remove o perfil de jogador e as partidas associadas a ele.
    public boolean deletarPerfil(int id){
        historicoDAO.deletarById(id);
        return jogadorDAO.deletarPerfil(id);
    }
    
    // Retorna o nome do perfil atualmente selecionado
    // Em caso de nenhum perfil esteja selecionado, retorna null.
    public String buscarNome() {
        RegistroJogador perfil = jogadorDAO.getPerfilSelecionado();
    return (perfil != null) ? perfil.getName() : null;
    }
    
    // Altera o nome de um jogador
    public boolean alterarNomePerfil(int id, String novoNome){
        return jogadorDAO.atualizarNome(id, novoNome);
    }
    
    // Busca um jogador pelo seu ID.
    public RegistroJogador buscarJogadorId(int id){
        return jogadorDAO.buscarPerfil(id);
    }
}
