package model.DAO;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;

/**
 *  Classe responsável pelo gerencimento dos perfis de jogadores.
 * 
 * A classse implementa persistência de dados utilizando serialization em arquivo.
 * Permitindo criar, consultar, selecionar e remover perfis de jogadores.
 * 
 * Além de gerenciar registro, a classe faz controle do perfil selecionado.
 * 
 * @author gbrsnts
 */

public class JogadorDAO implements Serializable {

    private static final String ARQUIVO = "registros/jogador.dat";
    private HashMap<Integer, RegistroJogador> jogadores;
    private int perfilSelecionadoId = -1;
    
    // Cria uma instância de jogador e carrega os dados do arquivo.
    public JogadorDAO(){
        carregar();
    }
    
    // Carrega os dados dos jogador a partir do arquivo de persistência.
    private void carregar(){
        File arquivo = new File(ARQUIVO);
        
        // Valida se o arquivo existe, caso contrário inicializa um HashMap vazio.
        if(!arquivo.exists()){
            jogadores = new HashMap<>();
            return;
        }
        try(ObjectInputStream in =
            new ObjectInputStream(
                new FileInputStream(arquivo))){
            
            // Recupera o HashMap serializado
            jogadores = (HashMap<Integer, RegistroJogador>) in.readObject();
            
            int maiorId = 0;
            
            // Procura o maior ID já utilizado
            for(RegistroJogador perfil : jogadores.values()){
                if(perfil.getId() > maiorId){
                    maiorId = perfil.getId();
                }
            }
            
            RegistroJogador.atualizarId(maiorId + 1);
        } catch(Exception e){
            // Em caso de falha, cria um HashMap vazio
            jogadores = new HashMap<>();
        }
    }

    // Salva todos os jogadores em arquivo utilizando serialização.
    public void salvar(){
       try(ObjectOutputStream out = 
            new ObjectOutputStream(new FileOutputStream(ARQUIVO))){
           
            // Serializa e grava o HashMap completo.
            out.writeObject(jogadores);
            
       } catch(IOException e){
           System.out.println("Erro ao salvar jogadores.");
       }
    }
    
    // CREATE - Cria um perfil de jogador.
    public RegistroJogador criarRegistro(String nome){
        
        // Cria um novo perfil.
        RegistroJogador registro = new RegistroJogador(nome);
        
        // Armazena utilizando o ID como chave do HashMap.
        jogadores.put(registro.getId(), registro);
        
        // Salva a alteração no arquivo.
        salvar();
        
        // Retorna o jogador criado.
        return registro;
    }
    
    // READ - Retorna todos os perfis de jogadores já cadastrados.
    public Collection<RegistroJogador> listarPerfis(){
        return jogadores.values();
    }

    // READ - Busca um perfil pelo seu ID(Chave).
    public RegistroJogador buscarPerfil(int id){
        return jogadores.get(id);
    }
    
    // UPDATE - Atualiza o nome do jogador
    public boolean atualizarNome(int id, String novoNome){
        
        // Busca o jgoador no HashMap pelo ID
        RegistroJogador jogador = jogadores.get(id);
        
        // Verifica se o jogador existe antes de atualizar
        if (jogador == null){
            return false;
        }
        
        // Atualiza o atributo de nome do registro
        jogador.setName(novoNome);
        
        // Salva a alteração no arquivo.
        salvar();
        
        return true;
    }

    // DELETE - Remove um perfil do sistema.
    public boolean deletarPerfil(int id) {
        
        // Remove o jgoador.
        RegistroJogador removido = jogadores.remove(id);
        
        // Se o perfil removido era o selecionado
        // Também remove a seleção atual
        if(id == perfilSelecionadoId){
            perfilSelecionadoId = -1;
        }
        
        // Salva as alterações
        salvar();
        
        // Verifica se houve remoção
        return removido != null;
    }

    // SELECT - Define qual perfil será utilizado.
    public boolean selecionarPerfil(int id){
        // Verifica se o jogador existe
        if (!jogadores.containsKey(id))
            return false;
        
        // Define o perfil ativo.
        perfilSelecionadoId = id;
        
        return true;
    }
    
    // Informa o perfil atual selecionado.
    public RegistroJogador getPerfilSelecionado(){
        return jogadores.get(perfilSelecionadoId);
    }
}