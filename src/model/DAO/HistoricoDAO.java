package model.DAO;

import model.entity.RegistroPartida;
import model.entity.RegistroJogador;
import java.util.Collection;
import java.util.Objects;

/**
 * Classe responsável pelo gerencimento do histórico de partidas.
 * 
 * A classse implementa persistência para os registros de partida,
 * utilizando serialization em arquivo.
 * Permitindo adicionar, listar e remover registros do histórico.
 * 
 * @author gbrsnts
 */

public class HistoricoDAO extends RepositorioArquivo<RegistroPartida>{
   
    public HistoricoDAO(){
        super("registros/historico.dat");
    }

    // CREATE - Adiciona um novo registro de partida no histórico.
    public void adicionar(RegistroJogador perfil, Integer pontosJogador, Integer pontosMaquina, Boolean venceu) {        
        // Salva a alteração no arquivo.
        salvar(new RegistroPartida(perfil, pontosJogador, pontosMaquina, venceu));
    }
    
    // READ - Retorna todos os registros de partidas já armazenados.
    public Collection<RegistroPartida> listarHistorico(){
        return listarTodos();
    }

    // READ - Verifica se existe pelo menos um registro no histórico.
    public boolean existe(){
        return !dados.isEmpty();
    }
    
    // DELETE - Remove todas as partidas associadas a um jogador específico.
    public boolean deletarById(Integer jogadorId) {
        return removerSe(p -> Objects.equals(p.getJogadorId(), jogadorId)) > 0;
    }
}