package br.edu.up.todolist.view;

import java.util.Scanner;

public class ToDoView {
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

            op = scanner.nextInt();

        }while(op != 0);

    }
}
