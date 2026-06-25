package controller;

import model.DAO.HistoricoDAO;
import model.DAO.JogadorDAO;
import model.DAO.RegistroJogador;
import model.DAO.RegistroPartida;
import controller.JogoController;
import java.util.Collection;

/**
 *
 * @author gbrsnts
 */
public class MenuController {
    private HistoricoDAO historicoDAO;
    private JogadorDAO jogadorDAO;
    private JogoController jogoController;
    
   public MenuController() {
       historicoDAO = new HistoricoDAO();
       jogadorDAO = new JogadorDAO();
       jogoController = new JogoController(historicoDAO);
   }
    
    public void iniciarPartida() {
        RegistroJogador perfil = jogadorDAO.getPerfilSelecionado();
        jogoController.iniciarPartida(perfil);
    }
    
    /*
    Interação com DAO do histórico.
    */    
    public Collection<RegistroPartida> listarHistorico(){
        return historicoDAO.listarHistorico();
    }
    
    /*
    Interação com DAO do Jogador.
    */
    public void criarPerfil(String nome) {
        RegistroJogador registro = jogadorDAO.criarRegistro(nome);
        jogadorDAO.selecionarPerfil(registro.getId());
    }
    
    public Collection<RegistroJogador> listarPerfis(){
        return jogadorDAO.listarPerfis();
    }
    
    public boolean selecionarPerfil(int id){
        return jogadorDAO.selecionarPerfil(id);
    }
    
    public boolean deletarPerfil(int id){
        historicoDAO.deletarById(id);
        return jogadorDAO.deletarPerfil(id);
    }
    
    public String buscarNome() {
        RegistroJogador perfil = jogadorDAO.getPerfilSelecionado();
    return (perfil != null) ? perfil.getName() : null;
    }
    
    public RegistroJogador getPerfilSelecionado() {
        return jogadorDAO.getPerfilSelecionado();
    }
    
    public RegistroJogador buscarJogadorId(int id){
        return jogadorDAO.buscarPerfil(id);
    }
}
