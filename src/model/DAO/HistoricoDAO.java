package model.DAO;

import java.io.*;
import java.io.File;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author gbrsnts
 */

public class HistoricoDAO {

    private static final String ARQUIVO = "registros/historico.dat";
    private HashMap<Integer, RegistroPartida> historico;
    
    public HistoricoDAO(){
        carregar();
    }

    public void carregar() {
        File arquivo = new File(ARQUIVO);
        
        if(!arquivo.exists()){
            historico = new HashMap<>();
            return;
        }

        try (ObjectInputStream in =
                new ObjectInputStream(new FileInputStream(arquivo))){
            
            historico = (HashMap<Integer, RegistroPartida>) in.readObject();
            
            int maiorId = 0;
            
            for(RegistroPartida registro : historico.values()){
                if(registro.getId() > maiorId){
                    maiorId = registro.getId();
                }
            }
            
            RegistroPartida.atualizarId(maiorId + 1);
            
        } catch (Exception e) {
            historico = new HashMap<>();
        }
    }

    public void salvar() {
        try (ObjectOutputStream out =
                new ObjectOutputStream(new FileOutputStream(ARQUIVO))){
            
            out.writeObject(historico);
            
        } catch (IOException e) {
            System.out.println("Erro ao salvar histórico.");
        }
    }

    // CREATE
    public void adicionar(int jogadorId, String placar) {
        RegistroPartida registro = new RegistroPartida(jogadorId, placar);
        historico.put(registro.getId(), registro);
        salvar();
    }

    // READ ALL
    public Collection<RegistroPartida> listarHistorico(){
        return historico.values();
    }
    
    // DELETE
    public boolean deletarById(int jogadorId) {
        int tamanhoAntes = historico.size();
        
        historico.entrySet().removeIf(
        entry -> entry.getValue().getJogadorId() == jogadorId
        );
        
        if(historico.size() != tamanhoAntes){
            salvar();
            return true;
        }
        return false;
    }

    
    public boolean existe(){
        return !historico.isEmpty();
    }
    
    
}