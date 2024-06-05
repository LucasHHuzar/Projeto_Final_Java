package br.edu.up.todolist.controllers;

import br.edu.up.todolist.daos.TarefaDaos;
import br.edu.up.todolist.models.Tarefa;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public abstract class TarefaController {
    private static final Logger logger = LogManager.getLogger(TarefaController.class);

    private static final String TAREFA_FILE_NAME = "tarefa.txt";

    public static void cadastrar(Tarefa tarefa){
        TarefaDaos.escrever(TAREFA_FILE_NAME, List.of(tarefa), true);
    }

    public static List<Tarefa> listar(){
        var tarefas = TarefaDaos.listarTarefas(TAREFA_FILE_NAME);
        return tarefas;
    }
}
