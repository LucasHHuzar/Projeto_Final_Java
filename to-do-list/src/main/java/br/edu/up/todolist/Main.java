package br.edu.up.todolist;

import br.edu.up.todolist.utils.Util;
import br.edu.up.todolist.view.ToDoView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) {

        int op;
        Scanner scanner = new Scanner(System.in);
        do{

            System.out.println("**********************");
            System.out.println("        MENU          ");
            System.out.println("**********************");
            System.out.println("0 - Sair");
            System.out.println("1 - To Do List");

            op = Util.lerOpcoesMenu(scanner);
            exibirView(scanner, op);

        }while(op != 0);

        logger.info("Hello World!");

    }

    private static void exibirView(Scanner scanner, int op){

        switch (op){

            case 0:
                System.out.println("Tchauuuuuuuu");
                break;

            case 1:
                //Preciso chamar o ToDo view ToDoView
                ToDoView.iniciar(scanner);
                break;

            case 99:
                System.out.println("Você precisa informar um valor inteiro!");
                break;

            default:
                System.out.println("Opção Inválida! Escolha uma opção conforme foi pedido");

        }
    }

}