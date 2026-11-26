package model.DAO;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Interface para uso 
 * @author gbrsnts
 */
public interface Repositorio<T, ID> {
    // CREATE/UPDATE - Salva a entidade. Se o ID for null, gera um novo.
    T salvar(T entidade);
    
    // READ - Busca uma entidade pelo ID.
    Optional<T> buscarPorId(ID id);
    
    // READ - Retorna todas as entidades.
    List<T> listarTodos();
    
    // DELETE - Remove a entidade com o ID informado.
    boolean removerPorId(ID id);
    
    // DELETE - Remove todas as entidade que atendem uma condição.
    // Retorna a quantidade removida.
    int removerSe(Predicate<T> condicao);    
}
