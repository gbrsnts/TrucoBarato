package model.DAO;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author gbrsnts
 */

public class JogadorDAO implements Serializable {

    private static final String ARQUIVO = "registros/jogador.dat";
    private HashMap<Integer, RegistroJogador> jogadores;
    private int perfilSelecionadoId = -1;
    
    public JogadorDAO(){
        carregar();
    }
    
    private void carregar(){
        File arquivo = new File(ARQUIVO);
        
        if(!arquivo.exists()){
            jogadores = new HashMap<>();
            return;
        }
        try(ObjectInputStream in =
            new ObjectInputStream(
                new FileInputStream(arquivo))){
            
            jogadores = (HashMap<Integer, RegistroJogador>) in.readObject();
            
            int maiorId = 0;
            
            for(RegistroJogador perfil : jogadores.values()){
                if(perfil.getId() > maiorId){
                    maiorId = perfil.getId();
                }
            }
            
            RegistroJogador.atualizarId(maiorId + 1);
        } catch(Exception e){
            jogadores = new HashMap<>();
        }
    }

    public void salvar(){
       try(ObjectOutputStream out = 
            new ObjectOutputStream(
                new FileOutputStream(ARQUIVO))){
           
            out.writeObject(jogadores);
       } catch(IOException e){
           System.out.println("Erro ao salvar jogadores.");
       }
    }
    
    // CREATE
    public RegistroJogador criarRegistro(String nome){
        RegistroJogador registro = new RegistroJogador(nome);
        
        jogadores.put(registro.getId(), registro);
        
        salvar();
        
        return registro;
    }
    
    // READ ALL
    public Collection<RegistroJogador> listarPerfis(){
        return jogadores.values();
    }

    // READ
    public RegistroJogador buscarPerfil(int id){
        return jogadores.get(id);
    }

    // DELETE
    public boolean deletarPerfil(int id) {
        RegistroJogador removido = jogadores.remove(id);
        salvar();
        return removido != null;
    }

    // SELECT PROFILE
    public boolean selecionarPerfil(int id){
        if (!jogadores.containsKey(id))
            return false;
        
        perfilSelecionadoId = id;
        
        return true;
    }
    
    public RegistroJogador getPerfilSelecionado(){
        return jogadores.get(perfilSelecionadoId);
    }
    
    public boolean possuiPerfilCriado(){
        return !jogadores.isEmpty();
    }
}