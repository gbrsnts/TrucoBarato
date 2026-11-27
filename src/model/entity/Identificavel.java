package model.entity;



/**
 * 
 * Interface genérica para objetos que possuem um identificador.
 * O tipo do ID é definido por quem implementa.
 * 
 * @author gbrsnts
 */
public interface Identificavel<ID> {
    
    // Retorna o identificador do objeto.
    ID getId();
    
    // Define o identificador do objeto (usado para salvar).
    void setId(ID id);
    
}
