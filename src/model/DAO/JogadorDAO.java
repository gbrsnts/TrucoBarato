package model.DAO;

import model.entity.RegistroJogador;
import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

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

public class JogadorDAO extends RepositorioArquivo<RegistroJogador> {
    private Integer perfilSelecionadoId;
    
    // Cria uma instância de jogador e carrega os dados do arquivo.
    public JogadorDAO(){
        super("registros/jogador.dat");
    }
    
    // CREATE - Cria um perfil de jogador.
    public RegistroJogador criarRegistro(String nome){
        return salvar(new RegistroJogador(nome));
    }
    
    // READ - Retorna todos os perfis de jogadores já cadastrados.
    public Collection<RegistroJogador> listarPerfis(){
        return listarTodos();
    }

    // READ - Busca um perfil pelo ID.
    public Optional<RegistroJogador> buscarPerfil(Integer id){
        return buscarPorId(id);
    }
    
    // UPDATE - Atualiza o nome do jogador
    public boolean atualizarNome(Integer id, String novoNome){
        
        // Busca o jogador no HashMap pelo ID
        Optional<RegistroJogador> jogador = buscarPerfil(id);
        
        // Verifica se o jogador existe antes de atualizar
        if (jogador.isEmpty()){
            return false;
        }
        
        // Atualiza o atributo de nome do registro
        jogador.get().setName(novoNome);
        
        // Salva a alteração no arquivo.
        salvar(jogador.get());        
        return true;
    }

    // DELETE - Remove um perfil do sistema.
    public boolean deletarPerfil(Integer id) {
        
        // Remove o jogador.
        boolean removido = removerPorId(id);
        
        // Se o perfil removido era o selecionado
        // Também limpa a seleção atual
        if(Objects.equals(id, perfilSelecionadoId)){
            perfilSelecionadoId = null;
        }
        
        return removido;
    }

    // SELECT - Define qual perfil será utilizado.
    public boolean selecionarPerfil(Integer id){
        // Verifica se o jogador existe
        if (buscarPorId(id).isEmpty()) {
            return false;
        }
        
        // Define o perfil ativo.
        perfilSelecionadoId = id;
        return true;
    }
    
    // Informa o perfil atual selecionado.
    // buscarPorId já devolve Optional
    public Optional<RegistroJogador> getPerfilSelecionado(){
        return Optional.ofNullable(perfilSelecionadoId).flatMap(this::buscarPorId);
    }
}