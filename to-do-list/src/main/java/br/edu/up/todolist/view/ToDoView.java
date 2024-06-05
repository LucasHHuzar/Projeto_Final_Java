package br.edu.up.todolist.view;

import br.edu.up.todolist.controllers.TarefaController;
import br.edu.up.todolist.controllers.UsuarioController;
import br.edu.up.todolist.models.Tarefa;
import br.edu.up.todolist.utils.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.CharArrayReader;
import java.util.Scanner;
import java.util.UUID;

public class ToDoView {

    private static final Logger logger = LogManager.getLogger(ToDoView.class);
    public static void iniciar(Scanner scanner) {

        int op;
        do {
            exibirMenu();
            op = Util.lerOpcoesMenu(scanner);
            exibirEscolha(scanner, op);
        }while (op != 0);
    }
    public static void exibirMenu(){

        System.out.println("**********************");
        System.out.println("      To-Do-List      ");
        System.out.println("**********************");
        System.out.println("0 - Sair");
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Atualizar");
        System.out.println("3 - Remover");
        System.out.println("4 - Listar");

    }
    private static void exibirEscolha(Scanner scanner, int op){

        switch (op){

            case 0:
                System.out.println("Tchauuuuuuuu");
                break;

            case 1:
                cadastrar(scanner);
                break;

                case 2:
                atualizar(scanner);
                break;

                case 3:
                remover(scanner);
                break;

                case 4:
                listar();
                break;

            case 99:
                System.out.println("Você precisa informar um valor inteiro!");
                break;

            default:
                System.out.println("Opção Inválida! Escolha uma opção conforme foi pedido");

        }
    }

    private static void cadastrar(Scanner scanner){
        try {
            System.out.println("Digite o titulo: ");
            var titulo = scanner.nextLine();

            System.out.println("Digite a descrição: ");
            var descricao = scanner.nextLine();

            System.out.println("Digite a prioridade: ");
            var prioridade = scanner.nextLine();

            UsuarioView.exibirDadosUsuarios();

            System.out.println("Escolha o usuario por UUID: ");
            var uuid = scanner.nextLine();

            //Buscando os dados dos usuários
            var usuario = UsuarioController.buscarUsuarioPorUUID(UUID.fromString(uuid));

            //Criando o objeto tarefa
            var tarefa = new Tarefa(titulo, descricao, prioridade, usuario);

            //Salvando o objeto tarefa
            TarefaController.cadastrar(tarefa);
        }catch (Exception ex){
            logger.error("Ocorreu um erro ao tentar criar uma tarefa", ex);
        }
    }

    private static void atualizar(Scanner scanner){
        try {
            listar();

            System.out.println("Qual tarefa você quer atualizar? ");
            var uuid = scanner.nextLine();

            System.out.println("#####################################");
            System.out.println("ATUALIZAÇÃO");
            System.out.println("#####################################");

            System.out.println("Digite a descrição: ");
            var descricao = scanner.nextLine();

            System.out.println("Digite a prioridade: ");
            var prioridade = scanner.nextLine();

            var tarefa = new Tarefa();
            tarefa.setDescricao(descricao);
            tarefa.setPrioridade(prioridade);

            //Salvando o objeto tarefa
            TarefaController.atualizar(UUID.fromString(uuid), tarefa);
        }catch (Exception ex){
            logger.error("Ocorreu um erro ao tentar criar uma tarefa", ex);
        }
    }
    private static void remover(Scanner scanner){

        listar();

        System.out.println("Qual tarefa você quer remover? ");
        var uuid = scanner.nextLine();

        TarefaController.remover(UUID.fromString(uuid));

    }

    private static void listar(){
        var tarefas = TarefaController.listar();

        System.out.println("========== LISTA DE TAREFAS ==============");

        tarefas.forEach(tarefa -> {
            System.out.println("UUID: " + tarefa.getUuid());
            System.out.println("NOME: " + tarefa.getTitulo());
        });

        System.out.println("========= LISTA DE TAREFAS ===============");
    }



}
