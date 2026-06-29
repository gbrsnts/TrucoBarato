package model.DAO;

import java.io.*;
import java.io.File;
import java.util.Collection;
import java.util.HashMap;

/**
 * Classe responsável pelo gerencimento do histórico de partidas.
 * 
 * A classse implementa persistência para os registros de partida,
 * utilizando serialization em arquivo.
 * Permitindo adicionar, listar e remover registros do histórico.
 * 
 * @author gbrsnts
 */

public class HistoricoDAO {

    private static final String ARQUIVO = "registros/historico.dat";
    private HashMap<Integer, RegistroPartida> historico;
    
    // Cria uma instância do histórico e carrega os dados do arquivo.
    public HistoricoDAO(){
        carregar();
    }

    // Carrega o histórico de partidas armazenado no arquivo.
    public void carregar() {
        File arquivo = new File(ARQUIVO);
        
        // Valida se o arquivo existe, caso contrário inicializa um histórico vazio.
        if(!arquivo.exists()){
            historico = new HashMap<>();
            return;
        }

        try (ObjectInputStream in =
                new ObjectInputStream(new FileInputStream(arquivo))){
            
            // Recupera o HashMap serializado
            historico = (HashMap<Integer, RegistroPartida>) in.readObject();
            
            int maiorId = 0;
            
            // Procura o maior ID já utilizado
            for(RegistroPartida registro : historico.values()){
                if(registro.getId() > maiorId){
                    maiorId = registro.getId();
                }
            }
            
            // Atualiza o próximo ID disponível
            RegistroPartida.atualizarId(maiorId + 1);
            
        } catch (Exception e) {
            // Em caso de falha, cria um histórico vazio
            historico = new HashMap<>();
        }
    }

    // Salva o histórico atual em arquivo utilizando serialização.
    public void salvar() {
        try (ObjectOutputStream out =
                new ObjectOutputStream(new FileOutputStream(ARQUIVO))){
            
            // Serializa e grava o HashMap completo.
            out.writeObject(historico);
            
        } catch (IOException e) {
            System.out.println("Erro ao salvar histórico.");
        }
    }

    // CREATE - Adiciona um novo registro de partida no histórico.
    public void adicionar(RegistroJogador perfil, String placar) {
        
        // Cria um novo registro de partida.
        RegistroPartida registro = 
                new RegistroPartida(perfil, placar);
        
        // Armazena o registro utilizando o ID como chave.
        historico.put(registro.getId(), registro);
        
        // Salva a alteração no arquivo.
        salvar();
    }
    
    // READ - Retorna todos os registros de partidas já armazenados.
    public Collection<RegistroPartida> listarHistorico(){
        return historico.values();
    }

    // READ - Verifica se existe pelo menos um registro no histórico.
    public boolean existe(){
        return !historico.isEmpty();
    }
    
    // DELETE - Remove todas as partidas associadas a um jogador específico.
    public boolean deletarById(int jogadorId) {
        
        // Guarda a quantidade de registros antes da remoção.
        int tamanhoAntes = historico.size();
        
        // Remove todas as partidas pertencentes ao jogador.
        historico.entrySet().removeIf(
        entry -> entry.getValue().getJogadorId() == jogadorId
        );
        
        // Verifica se houve alteração no tamanho.
        if(historico.size() != tamanhoAntes){
            
            // Salva as aalterações realizadas.
            salvar();
            return true;
        }
        return false;
    }
}