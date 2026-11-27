package view.game;

import controller.MenuController;
import java.util.Collection;
import model.entity.RegistroJogador;
import model.entity.RegistroPartida;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import view.util.Input;

/**
 * Classe do jogo responsável pela interação com o usuário.
 * Função de exibir o menu principal, interagir com o usuário
 * e e encaminhar as solicitações ao MenuController.
 * 
 * @author gbrsnts 
 */
public class ViewMenu {    
    private MenuController controller;
    
    
    // Inicializa a view associando-a ao controller recebido.
    public ViewMenu(MenuController controller){
        this.controller = controller;
    }
    
    // Exibe o menu principal e encaminha as opções aos métodos correspondentes.
    public void iniciar(){
        while(true){
            System.out.println("=== Bem-vindo(a) ao TrucoBarato! ===");
            System.out.println("[1] Criar perfil");
            System.out.println("[2] Selecionar perfil");
            System.out.println("[3] Jogar");
            System.out.println("[4] Historico");
            System.out.println("[5] Alterar nome do perfil");
            System.out.println("[6] Deletar perfil");
            System.out.println("[7] Regras");
            System.out.println("[8] Sair");
            
            int opcao = Input.lerInt("Escolha uma opcao: ");
            switch(opcao){
                case 1:
                    criarPerfil();
                    break;
                case 2:
                    selecionarPerfil();
                    break;
                case 3:
                    iniciarPartida();
                    break;
                case 4:
                    exibirHistorico();
                    break;
                case 5:
                    alterarNomePerfil();
                    break;
                case 6:
                    deletarPerfil();
                    break;
                case 7:
                    mostrarRegras();
                    break;
                case 8:
                    System.out.println("Encerrando jogo...");
                    return;
                default:
                    System.out.println("Opcao invalida!");
            }
        }    
    }
    
    // Exibe as regras do jogo.
    private void mostrarRegras(){
        System.out.println("\n=== Regras do TrucoBarato! ===");
        System.out.println("1. Cada jogador recebe 3 cartas por rodada.");
        System.out.println("2. Em cada turno, os dois jogadores jogam uma carta.");
        System.out.println("3. A carta de maior valor vence o turno.");
        System.out.println("4. Em caso de empate, vence o jogador que iniciou o turno.");    
        System.out.println("5. Quem vencer 2 turnos ganha a rodada.");
        System.out.println("6. Quem vencer 5 rodadas vence a partida.");
        System.out.println("7. Para desistir, digite 0 na escolha de carta.");
        System.out.println("Boa sorte e divirta-se!\n");
    }
    
    // Exibe o histórico de partidas anteriormente registradas.
    private void exibirHistorico(){
        Collection<RegistroPartida> historico = controller.listarHistorico();
        
        if (historico.isEmpty()) {
            System.out.println("Nenhuma partida registrada.");
            return;
        }
        
        DateTimeFormatter formatter = 
        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        
        System.out.println("\n=== Historico TrucoBarato! ===");
        
        // Copia para uma lista, que pode ser ordenada.
        List<RegistroPartida> ordenado = new ArrayList<>(historico);
        
        /* Classe anônima
        * Implementa Comparator
        * Ordena da partida mais recente para a mais antiga.
        */
        
        ordenado.sort(new Comparator<RegistroPartida>() {
            @Override
            public int compare(RegistroPartida a, RegistroPartida b) {
                return b.getPlayedAt().compareTo(a.getPlayedAt());
            }
        });
        
        // Function: recebe um RegistroPartida e devolve a linha formatada.
        Function<RegistroPartida, String> formatar = h -> {
            String nome = controller.buscarJogadorId(h.getJogadorId())
                    .map(RegistroJogador::getName)
                    .orElse("(perfil removido)");
            
            String resultado = Boolean.TRUE.equals(h.getVenceu()) ? "VITORIA" : "DERROTA";
            
            return "[" + h.getPlayedAt().format(formatter) + "] "
                    + nome + " "
                    + h.getPontosJogador() + " x " + h.getPontosMaquina()
                    + " Stuart - " + resultado;
        };
        
        // forEach recebe um Consumer: para cada partida, formata e imprime.
        ordenado.forEach(h -> System.out.println(formatar.apply(h)));
    }
    
    // Exibe todos os perfis cadastrados no sistema.
    private boolean listarPerfis(){
        Collection<RegistroJogador> perfis = controller.listarPerfis();
         if (perfis == null || perfis.isEmpty()) {
            System.out.println("Nenhum perfil cadastrado.");
            return false;
        }
        
        System.out.println("\n=== PERFIS ===");
        for (RegistroJogador p : perfis) {
            System.out.println(p);
        }
        return true;
    }
    
    // Remove um perfil e seu histórico associado, solicitando confirmação.
    private void deletarPerfil(){
        if(!listarPerfis()) return;
        
        int id = Input.lerInt("Digite o ID para deletar: ");
        
        Optional<RegistroJogador> perfil = controller.buscarJogadorId(id);
        
        if(perfil.isEmpty()){
            System.out.println("ID invalido!");
            return;
        }
        
        
        char confirma = Input.lerChar(
                "Deseja realmente deletar o perfil "
                + perfil.get().getName() 
                + " e seu historico? [S/N] ");
        
        if (confirma != 's') {
            System.out.println("Operacao cancelada.");
            return;
        }
        
        controller.deletarPerfil(id);
        System.out.println("Perfil deletado!");
    }
    
    // Permite o usuário selecionar um perfil previamente cadastrado.
    private void selecionarPerfil(){
        if(!listarPerfis()) return;
        
        int id = Input.lerInt("Digite o ID para selecionar: ");
        
        if (controller.selecionarPerfil(id)) {
            System.out.println("Perfil " + controller.buscarNome().orElse("")
                + " selecionado!");
        } else {
            System.out.println("ID invalido!");
        }
    }
    
    // Permite o usuário alterar o nome de um perfil.
    private void alterarNomePerfil(){
        if(!listarPerfis()) return;
        
        int id = Input.lerInt("Digite o ID do perfil: ");
        
        if(controller.buscarJogadorId(id).isEmpty()){
            System.out.println("ID invalido!");
            return;
        }
        
        String novoNome = Input.lerString("Digite o novo nome: ");
        
        boolean ok = controller.alterarNomePerfil(id, novoNome);
        
        if(ok){
            System.out.println("Nome alterado com sucesso!");
        } else {
            System.out.println("Erro ao alterar nome.");
        }
    }
    
    // Cria um novo perfil de jogador.
    private void criarPerfil(){
        String nome = Input.lerString("Digite o novo nome para seu perfil: ");
        try{
            controller.criarPerfil(nome);
            System.out.println("Perfil " + nome + " criado.");
        } catch (Exception e){
            System.out.println("Erro inesperado ao criar perfil.");
        }
    }
    
    // Inicia um nova partida utilizando o perfil atual selecionado.
    private void iniciarPartida(){
        if (controller.buscarNome().isEmpty()) {
            System.out.println("Selecione um perfil antes de jogar!");
            return;
        }
        
        Input.linha();
        System.out.println("Vamos comecar!");
       
        // Inicia a partida a partir de controller.
        controller.iniciarPartida();
    }
}
