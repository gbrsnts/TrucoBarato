package model.DAO;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Implementação genérica de Repositorio que persiste os dados em arquivo
 * utilizando serialização.
 * 
 * Funções de carregar, gravar e gerar próximo ID.
 * 
 * @author gbrsnts
 */
public abstract class RepositorioArquivo<T extends Identificavel<Integer> & Serializable> implements Repositorio<T, Integer> {
    private final String arquivo;
    protected HashMap<Integer, T> dados = new HashMap<>();
    
    protected RepositorioArquivo(String arquivo){
        this.arquivo = arquivo;
        carregar();
    }
    
    // Carrega o HashMap serializado do arquivo se existir.
    @SuppressWarnings("unchecked")
    private void carregar() {
        File f = new File(arquivo);

        if (!f.exists()) {
            return;
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(f))) {
            dados = (HashMap<Integer, T>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar " + arquivo + ". Iniciando vazio.");
            dados = new HashMap<>();
        }
    }

    // Grava o HashMap completo no arquivo.
    protected void gravarArquivo() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            out.writeObject(dados);
        } catch (IOException e) {
            System.out.println("Erro ao salvar " + arquivo + ".");
        }
    }

    // Gera o próximo ID disponível.
    private Integer proximoId() {
        // Converte o ID para int e pega o maior.
        return dados.keySet().stream()
                .mapToInt(Integer::intValue)
                .max()
                .orElse(0) + 1;
    }

    @Override
    public T salvar(T entidade) {
        // ID null = entidade nova, ainda não salva.
        if (entidade.getId() == null) {
            entidade.setId(proximoId());
        }
        dados.put(entidade.getId(), entidade);
        gravarArquivo();
        return entidade;
    }

    @Override
    public Optional<T> buscarPorId(Integer id) {
        return Optional.ofNullable(dados.get(id));
    }

    @Override
    public List<T> listarTodos() {
        // Devolve uma cópia para ninguém alterar o HashMap por fora.
        return new ArrayList<>(dados.values());
    }

    @Override
    public boolean removerPorId(Integer id) {
        boolean removido = dados.remove(id) != null;
        if (removido) {
            gravarArquivo();
        }
        return removido;
    }

    @Override
    public int removerSe(Predicate<T> condicao) {
        int antes = dados.size();
        dados.values().removeIf(condicao);
        int removidos = antes - dados.size();
        if (removidos > 0) {
            gravarArquivo();
        }
        return removidos;
    }
}
