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

        do{

            System.out.println("**********************");
            System.out.println("      To-Do-List      ");
            System.out.println("**********************");
            System.out.println("0 - Sair");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Alterar");
            System.out.println("3 - Remover");
            System.out.println("4 - Listar");

            op = Util.lerOpcoesMenu(scanner);
            exibirView(scanner, op);

        }while(op != 0);

    }
    private static void exibirView(Scanner scanner, int op){

        switch (op){

            case 0:
                System.out.println("Tchauuuuuuuu");
                break;

            case 1:
                cadastrar(scanner);
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

    private static void atualizar(){

    }

    private static void listar(){

    }



}
