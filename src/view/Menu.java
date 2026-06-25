package view;

import java.util.Scanner;
import java.util.InputMismatchException;
import controller.MenuController;
import java.util.Collection;
import model.DAO.RegistroJogador;
import model.DAO.RegistroPartida;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author gbrsnts 
 */
public class Menu {    
    private Scanner scanner = new Scanner(System.in);
    //Instancia o controller do menu
    private MenuController controller = new MenuController();
    
    public void iniciar(){
        while(true){
            System.out.println("=== Bem-vindo(a) ao TrucoBarato! ===");
            System.out.println("[1] Criar perfil");
            System.out.println("[2] Selecionar perfil");
            System.out.println("[3] Jogar");
            System.out.println("[4] Historico");
            System.out.println("[5] Deletar perfil");
            System.out.println("[6] Regras");
            System.out.println("[7] Sair");
            System.out.print("Escolha uma opcao: ");
            try{
                int opcao = scanner.nextInt();
                scanner.nextLine();
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
                        deletarPerfil();
                        break;
                    case 6:
                        mostrarRegras();
                        break;
                    case 7:
                        System.out.println("Encerrando jogo...");
                        return;
                    default:
                        System.out.println("Opcao invalida!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Digite apenas numeros!");
                scanner.nextLine();
            }
        }    
    }
    
    public void mostrarRegras(){
        System.out.println("\n=== Regras do TrucoBarato! ===");
        System.out.println("1. Cada jogador recebe 3 cartas por rodada.");
        System.out.println("2. Em cada turno, os dois jogadores jogam uma carta.");
        System.out.println("3. A carta de maior valor vence o turno.");
        System.out.println("4. Em caso de empate, vence o jogador que iniciou o turno.");    
        System.out.println("5. Quem vencer 2 turnos ganha a rodada.");
        System.out.println("6. Quem vencer 5 rodadas vence a partida.");
        System.out.println("Boa sorte e divirta-se!\n");
    }
    
    public Integer lerIntSeguro(String mensagem){
        while (true) {
            System.out.println(mensagem);
            String entrada = scanner.nextLine().trim();
            
            if(entrada.isEmpty()) {
                System.out.print("Entrada vazia! Digite um numero.");
                continue;
            }
            
            try{
                return Integer.parseInt(entrada);
            } catch(NumberFormatException e){
                System.out.println("Entrada invalida! Digite um numero.");
            }
        }
    }
    
    public char lerCharSeguro(String mensagem){
        while(true){
            System.out.println(mensagem);
            
            String entrada = scanner.nextLine().trim();
            
            if(entrada.isEmpty()){
                System.out.println("Entrada vazia! Digite 'S' ou 'N'.");
                continue;
            }
            
            char c = Character.toLowerCase(entrada.charAt(0));
            
            if (c == 's' || c == 'n'){
                return c;
            }
            
            System.out.println("Entrada inválida! Digite apenas 'S' ou 'N'.");
        }
    }
    
    public void exibirHistorico(){
        Collection<RegistroPartida> historico = controller.listarHistorico();
        
        if (historico.isEmpty()) {
            System.out.println("Nenhuma partida registrada.");
            return;
        }
        
        DateTimeFormatter formatter = 
        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        
        System.out.println("\n=== Historico TrucoBarato! ===");
        
        for (RegistroPartida h : historico) {
            RegistroJogador jogador = controller.buscarJogadorId(
            h.getJogadorId());
            
            System.out.println("[" + h.getPlayedAt().format(formatter)
                    + "] " + jogador.getName()
                    + " " + h.getPlacar()
                    + " Stuart");
        }
        
    }
    
    public boolean listarPerfis(){
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
    
    public void deletarPerfil(){
        if(!listarPerfis()) return;
        
        int id = lerIntSeguro("Digite o ID para deletar: ");
        
        RegistroJogador perfil = controller.buscarJogadorId(id);
        
        if(perfil == null){
            System.out.println("ID invalido!");
            return;
        }
        
        char confirma = lerCharSeguro(
                "Deseja realmente deletar o perfil "
                + perfil.getName() + "? [S/N] ");
        
        if (confirma != 's') {
            System.out.println("Operacao cancelada.");
            return;
        }
        
        controller.deletarPerfil(id);
        System.out.println("Perfil deletado!");
    }
    
    public void selecionarPerfil(){
        if(!listarPerfis()) return;
        
        int id = lerIntSeguro("Digite o ID para selecionar: ");
        
        if (controller.selecionarPerfil(id)) {
            System.out.println("Perfil " + controller.buscarNome() 
                + " selecionado!");
        } else {
            System.out.println("ID invalido!");
        }
    }
    
    public void criarPerfil(){
        System.out.print("Digite o novo nome para seu perfil: ");
        String nome = scanner.nextLine();
        try{
            controller.criarPerfil(nome);
            System.out.println("Perfil " + nome + " criado.");
        } catch (Exception e){
            System.out.println("Erro inesperado ao criar perfil.");
        }
    }
    
    public void iniciarPartida(){
        String nome = controller.buscarNome();
        if (nome == null) {
            System.out.println("Selecione um perfil antes de jogar!");
            return;
        }
        
        Tela.linha();
        System.out.println("Vamos comecar!");
       
        // Inicia a partida a partir de controller.
        controller.iniciarPartida();
    }
}
