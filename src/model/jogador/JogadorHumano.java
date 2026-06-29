package model.jogador;

import model.DAO.RegistroJogador;
import model.carta.Carta;

/**
 * Classe que representa o jogador controlado pela usuário.
 * Recebe um RegistroJogador e inicializa o jogador humano.
 *
 * @author gbrsnts
 */
public class JogadorHumano extends Jogador {    
    private RegistroJogador registro;
    
    public JogadorHumano(RegistroJogador registro) {
        
        // Define o nome do jogador na classe base
        super(registro.getName());
        
        // Guarda referência ao registro completo
        this.registro = registro;
    }
    
    // Retorna o registro completo do jogador.
    // Permitindo acessar informações persistidas como ID, nome e data de criação.
    public RegistroJogador getRegistro(){
        return registro;
    }
    
    /*
    Diferente da máquina, o jogador humano não escolhe aleatoriamente.
    Ele recebe um índice externo, vindo da interação do usuário.
    */
    @Override
    public Carta escolherCarta(int indice) {    
        return mao.remove(indice);
    }
}
